package dev.butter.mathbot.gui.panel

import dev.butter.mathbot.gui.frame.CONSOLE_PANEL_HEIGHT
import dev.butter.mathbot.gui.frame.FILE_PANEL_HEIGHT
import dev.butter.mathbot.gui.frame.GUI_WIDTH
import dev.butter.mathbot.gui.frame.PANEL_BACKGROUND
import dev.butter.mathbot.module.Addon
import javax.inject.Singleton
import javax.swing.JPanel

@Singleton
class ConsolePanel : JPanel(), Addon {
    override fun init() {
        setSize(GUI_WIDTH, CONSOLE_PANEL_HEIGHT)
        setLocation(0, FILE_PANEL_HEIGHT)
        background = PANEL_BACKGROUND
    }
}