package dev.butter.mathbot.data

import dev.butter.mathbot.data.DungeonState.*

object LogLineProcessor {
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

    var state: DungeonState = NOT_STARTED
        private set
    var accounts: Int = 0
        private set

    fun processStart(lines: List<String>) {
        val trimmedLines = lines.trimLines()
        val iterator = trimmedLines.iterator()

        while (iterator.hasNext()) {
            val next = iterator.next()

            if (next.contains(PORTAL_PLACEMENT) && state != IN_DUNGEON) {
                run { iterator.next() }

                val twoLineAfter = iterator.next()

                if (twoLineAfter.contains(NUL_PORTAL_PLACEMENT)) {
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

                EquationProcessor.equations += equation

                if (EquationProcessor.equations.size == accounts) {
                    EquationProcessor.evaluate()
                }
            } else if (next.contains(DUNGEON_FINISHED)) {
                val nextLine = iterator.next()

                if (nextLine.contains(NUL_DUNGEON_CLEARED)) {
                    state = NOT_STARTED
                    accounts = 0
                }
            } else if (next.contains(DUNGEON_END)) {
                val nextLine = iterator.next()

                if (nextLine.contains(OBJECTIVE_NOT_CLEARED)) {
                    state = NOT_STARTED
                    accounts = 0
                }
            } else if (next.contains(SAFE_ZONE_MESSAGE) && state == IN_DUNGEON) {
                accounts--

                if (accounts == 0) {
                    state = NOT_STARTED
                }
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

                EquationProcessor.equations += equation

                println("equations")
                if (EquationProcessor.equations.size >= accounts) {
                    EquationProcessor.evaluate()
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

    private fun List<String>.trimLines() = map { it.replace(LINE_TRIM_REGEX, "") }
}