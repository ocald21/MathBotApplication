package dev.butter.mathbot.data

import dev.butter.mathbot.data.DungeonState.*
import dev.butter.mathbot.module.Addon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogLineProcessor
@Inject
constructor(
    private val equationProcessor: EquationProcessor,
) : Addon {
    override fun init() = Unit

    var state: DungeonState = NOT_STARTED
        private set
    var accounts: Int = 0
        private set

    fun processStart(lines: List<String>) {
        val trimmedLines = lines.trimLines().reversed()
        val iterator = trimmedLines.iterator()

        while (iterator.hasNext()) {
            val next = iterator.next()

            if (next.contains(PORTAL_ENTERED)) {
                state = IN_DUNGEON
                accounts++
                println("Account: $accounts")
                continue
            }

            if (iterator.scan(next, OBJECTIVE_NOT_CLEARED, DUNGEON_END, 0) ||
                iterator.scan(next, NUL_DUNGEON_CLEARED, DUNGEON_FINISHED, 0)
            ) {
                state = NOT_STARTED
                accounts = 0
                return
            }

            if (iterator.scan(next, NUL_PORTAL_PLACEMENT, PORTAL_PLACEMENT, 2)) {
                if (state == NOT_STARTED) {
                    state = LOADING
                    accounts = 0
                }
                return
            }

            if (next.contains(SAFE_ZONE_MESSAGE) && state == IN_DUNGEON) {
                accounts--

                if (accounts == 0) {
                    state = NOT_STARTED
                    return
                }
            }

            if (next.contains(EQUATION_PREFIX)) {
                state = IN_MATH

                val equation = next.replace(EQUATION_PREFIX, "").trim()

                equationProcessor.equations += equation
            }
        }
    }

    fun processNew(lines: List<String>) {
        val trimmedLines = lines.trimLines()
        val iterator = trimmedLines.iterator()

        while (iterator.hasNext()) {
            val next = iterator.next()

            if (next.contains(PORTAL_PLACEMENT) && state != IN_DUNGEON) {
                run { iterator.next() }

                val twoLineAfter = iterator.next()

                if (twoLineAfter.contains(NUL_PORTAL_PLACEMENT)) {
                    println("portal placed")
                    state = LOADING
                }
            } else if (next.contains(PORTAL_ENTERED)) {
                state = IN_DUNGEON
                accounts++
                println("Account: $accounts")
            } else if (next.contains(MATH_OBJECTIVE)) {
                state = IN_MATH

                repeat(6) { iterator.next() }

                val equation = iterator.next().replace(EQUATION_PREFIX, "").trim()

                equationProcessor.equations += equation

                println("equations")
                if (equationProcessor.equations.size >= accounts) {
                    equationProcessor.evaluate()
                    println("evaluating")
                }
            } else if (next.contains(DUNGEON_FINISHED)) {
                val nextLine = iterator.next()

                if (nextLine.contains(NUL_DUNGEON_CLEARED)) {
                    state = NOT_STARTED
                    accounts = 0
                    println("nul cleared")
                }
            } else if (next.contains(DUNGEON_END)) {
                val nextLine = iterator.next()

                if (nextLine.contains(OBJECTIVE_NOT_CLEARED)) {
                    println("nul obj failed")
                    state = NOT_STARTED
                    accounts = 0
                }
            } else if (next.contains(SAFE_ZONE_MESSAGE) && state == IN_DUNGEON) {
                accounts--

                if (accounts <= 0) {
                    accounts = 0
                    state = NOT_STARTED
                }
            }
        }
    }

    private fun Iterator<String>.scan(
        next: String,
        firstFlag: String,
        secondFlag: String,
        distance: Int,
    ): Boolean {
        if (next.contains(firstFlag)) {
            repeat(distance - 1) { this.next() }

            val nextLine = this.next()

            if (nextLine.contains(secondFlag)) {
                return true
            }
        }

        return false
    }

    private fun List<String>.trimLines() = map { it.replace(LINE_TRIM_REGEX, "") }

    companion object {
        private val LINE_TRIM_REGEX = """^\[\d{2}:\d{2}:\d{2}] \[Client thread/INFO]: \[CHAT] """.toRegex()

        private const val PORTAL_PLACEMENT = "*** DUNGEON PORTAL ***"
        private const val NUL_PORTAL_PLACEMENT = "Planet Nul at:"

        private const val PORTAL_ENTERED = "(!) You have entered the Planet Nul /dungeon!"

        private const val MATH_OBJECTIVE = "(DUNGEON) NEW OBJECTIVE: Solve Escape Sequence"
        private const val EQUATION_PREFIX = "Your Equation:"

        private const val DUNGEON_FINISHED = "*-*-* DUNGEON COMPLETE! *-*-*"
        private const val NUL_DUNGEON_CLEARED = "The Planet Nul has been cleared by:"

        private const val DUNGEON_END = "Dungeon End"
        private const val OBJECTIVE_NOT_CLEARED = "The objective was not cleared fast enough."

        private const val SAFE_ZONE_MESSAGE = " ~ SafeZone - PvP is disabled here."
    }
}