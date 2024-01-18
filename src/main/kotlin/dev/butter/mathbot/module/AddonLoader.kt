package dev.butter.mathbot.module

import dev.butter.mathbot.gui.frame.Gui
import dev.butter.mathbot.gui.frame.SelectFileFrame
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddonLoader
@Inject
constructor(
    private val gui: Gui,
    private val addons: MutableSet<Addon>,
) : Addon {
    override fun init() {
        val addonsToSkip = listOf(
            SelectFileFrame::class,
            Gui::class,
        )

        addons
            .filter { it::class !in addonsToSkip }
            .forEach(Addon::init)

        gui.init()
    }
}