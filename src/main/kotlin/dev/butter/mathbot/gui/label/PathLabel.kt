package dev.butter.mathbot.gui.label

import dev.butter.mathbot.gui.panel.DISTANCE_FROM_TOP
import dev.butter.mathbot.module.Addon
import java.awt.Color
import java.awt.Font
import javax.inject.Singleton
import javax.swing.JLabel

const val PATH_LABEL_POSITION_X = 10
const val PATH_LABEL_WIDTH = 100

@Singleton
class PathLabel : JLabel(), Addon {
    override fun init() {
        setLocation(PATH_LABEL_POSITION_X, DISTANCE_FROM_TOP)
        setSize(PATH_LABEL_WIDTH, 50)
        text = "Path:"
        foreground = Color.cyan
        font = Font(Font.MONOSPACED, Font.BOLD, 20)
    }
}