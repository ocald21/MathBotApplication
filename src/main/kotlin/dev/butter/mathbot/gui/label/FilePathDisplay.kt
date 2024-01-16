package dev.butter.mathbot.gui.label

import dev.butter.mathbot.config
import dev.butter.mathbot.gui.button.SELECT_PATH_BUTTON_POSITION_X
import dev.butter.mathbot.gui.panel.DISTANCE_FROM_TOP
import dev.butter.mathbot.module.Addon
import java.awt.Color
import java.awt.Font
import javax.swing.JLabel
import javax.swing.border.LineBorder

const val FILE_PATH_POSITION_X = PATH_LABEL_POSITION_X + PATH_LABEL_WIDTH
const val FILE_PATH_WIDTH = SELECT_PATH_BUTTON_POSITION_X - FILE_PATH_POSITION_X
val FILE_PATH_FONT_COLOR: Color = Color(185, 91, 245)

class FilePathDisplay : JLabel("", CENTER), Addon {
    override fun init() {
        setLocation(FILE_PATH_POSITION_X, DISTANCE_FROM_TOP)
        setSize(FILE_PATH_WIDTH, 50)
        border = LineBorder(Color.cyan, 2)
        text = config.data.logFilePath
        foreground = FILE_PATH_FONT_COLOR
        font = Font(Font.SERIF, Font.BOLD, 18)
    }
}