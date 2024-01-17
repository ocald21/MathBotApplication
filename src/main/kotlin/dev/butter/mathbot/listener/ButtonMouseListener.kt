package dev.butter.mathbot.listener

import dev.butter.mathbot.gui.button.SelectPathButton
import dev.butter.mathbot.module.Addon
import java.awt.Color
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.inject.Inject

class ButtonMouseListener
@Inject
constructor(
    private val button: SelectPathButton,
) : MouseListener, Addon {
    private val defaultBackground: Color = Color.cyan
    private val hoveredBackground: Color = Color.green

    override fun init() = Unit

    override fun mouseClicked(e: MouseEvent) = Unit

    override fun mousePressed(e: MouseEvent) = Unit

    override fun mouseReleased(e: MouseEvent) {
        button.background = defaultBackground
    }

    override fun mouseEntered(e: MouseEvent) {
        button.background = hoveredBackground
    }

    override fun mouseExited(e: MouseEvent) {
        button.background = defaultBackground
    }
}