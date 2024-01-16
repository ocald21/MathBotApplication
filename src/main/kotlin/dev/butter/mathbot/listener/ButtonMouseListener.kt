package dev.butter.mathbot.listener

import java.awt.Color
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JButton

class ButtonMouseListener(
    private val button: JButton,
    private val defaultBackground: Color,
    private val hoveredBackground: Color,
) : MouseListener {
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