package dev.butter.mathbot.gui.button

import com.authzee.kotlinguice4.getInstance
import com.google.inject.Injector
import dev.butter.mathbot.gui.frame.GUI_WIDTH
import dev.butter.mathbot.gui.frame.SelectFileFrame
import dev.butter.mathbot.gui.label.FilePathDisplay
import dev.butter.mathbot.gui.panel.DISTANCE_FROM_TOP
import dev.butter.mathbot.listener.ButtonMouseListener
import dev.butter.mathbot.module.Addon
import java.awt.Color
import java.awt.Font
import javax.inject.Inject
import javax.inject.Singleton
import javax.swing.JButton

const val SELECT_PATH_BUTTON_WIDTH = 100
const val SELECT_PATH_BUTTON_HEIGHT = 50
const val SELECT_PATH_BUTTON_POSITION_X = GUI_WIDTH - SELECT_PATH_BUTTON_WIDTH - 50

@Singleton
class SelectPathButton
@Inject
constructor(
    private val filePathDisplay: FilePathDisplay,
    private val injector: Injector,
) : JButton(), Addon {
    private val defaultBackground: Color = Color.cyan
    private val hoveredBackground: Color = Color.green

    override fun init() {
        setLocation(SELECT_PATH_BUTTON_POSITION_X, DISTANCE_FROM_TOP)
        setSize(SELECT_PATH_BUTTON_WIDTH, SELECT_PATH_BUTTON_HEIGHT)
        isFocusPainted = false
        isBorderPainted = false
        text = "Set"
        foreground = Color.black
        background = defaultBackground
        font = Font(Font.MONOSPACED, Font.BOLD, 20)
        addActionListener { _ ->
            println("Selecting file...")
            SelectFileFrame(filePathDisplay).init()
        }
        addMouseListener(injector.getInstance<ButtonMouseListener>())
    }
}