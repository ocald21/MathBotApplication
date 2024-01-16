package dev.butter.mathbot.gui.frame

import dev.butter.mathbot.gui.panel.ConsolePanel
import dev.butter.mathbot.gui.panel.FilePanel
import dev.butter.mathbot.listener.ExitListener
import dev.butter.mathbot.module.Addon
import java.awt.Color
import javax.inject.Inject
import javax.swing.JFrame

const val GUI_WIDTH = 1000
const val GUI_HEIGHT = 600
const val FILE_PANEL_HEIGHT = 100
const val CONSOLE_PANEL_HEIGHT = GUI_HEIGHT - FILE_PANEL_HEIGHT
val PANEL_BACKGROUND = Color(26, 26, 26)

class Gui
@Inject
constructor(
    private val exitListener: ExitListener,
    private val filePanel: FilePanel,
    private val consolePanel: ConsolePanel,
) : JFrame(), Addon {
    override fun init() {
        title = "MathBot"
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        this.addWindowListener(exitListener)
        this.add(filePanel)
        this.add(consolePanel)
        setSize(GUI_WIDTH, GUI_HEIGHT)
        setLocationRelativeTo(null)
        isVisible = true
    }
}