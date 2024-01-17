package dev.butter.mathbot.gui

import dev.butter.mathbot.gui.button.SelectPathButton
import dev.butter.mathbot.gui.frame.Gui
import dev.butter.mathbot.gui.frame.SelectFileFrame
import dev.butter.mathbot.gui.label.FilePathDisplay
import dev.butter.mathbot.gui.label.PathLabel
import dev.butter.mathbot.gui.panel.ConsolePanel
import dev.butter.mathbot.gui.panel.FilePanel
import dev.butter.mathbot.module.BaseModule

class GuiModule : BaseModule() {
    override fun configure() {
        addBinding(
            ConsolePanel::class,
            FilePanel::class,
            FilePathDisplay::class,
            Gui::class,
            PathLabel::class,
            SelectFileFrame::class,
            SelectPathButton::class,
        )
    }
}