package dev.butter.mathbot.gui.button

import dev.butter.mathbot.gui.frame.GUI_WIDTH
import dev.butter.mathbot.gui.frame.SelectFileFrame
import dev.butter.mathbot.gui.label.FilePathDisplay
import dev.butter.mathbot.gui.panel.DISTANCE_FROM_TOP
import dev.butter.mathbot.listener.ButtonMouseListener
import java.awt.Color
import java.awt.Font
import javax.inject.Inject
import javax.swing.JButton

const val SELECT_PATH_BUTTON_WIDTH = 100
const val SELECT_PATH_BUTTON_HEIGHT = 50
const val SELECT_PATH_BUTTON_POSITION_X = GUI_WIDTH - SELECT_PATH_BUTTON_WIDTH - 50

class SelectPathButton
@Inject
constructor(
    private val filePathDisplay: FilePathDisplay,
) : JButton() {
    private val defaultBackground: Color = Color.cyan
    private val hoveredBackground: Color = Color.green

    init {
        setLocation(SELECT_PATH_BUTTON_POSITION_X, DISTANCE_FROM_TOP)
        setSize(SELECT_PATH_BUTTON_WIDTH, SELECT_PATH_BUTTON_HEIGHT)
        isFocusPainted = false
        isBorderPainted = false
        text = "Set"
        foreground = Color.black
        background = defaultBackground
        font = Font(Font.MONOSPACED, Font.BOLD, 20)
        addActionListener { _ ->
            SelectFileFrame(filePathDisplay)
        }
        addMouseListener(
            ButtonMouseListener(
                this,
                defaultBackground,
                hoveredBackground,
            )
        )
    }
}