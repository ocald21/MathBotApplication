package dev.butter.mathbot.gui.panel

import dev.butter.mathbot.gui.button.SelectPathButton
import dev.butter.mathbot.gui.frame.FILE_PANEL_HEIGHT
import dev.butter.mathbot.gui.frame.GUI_WIDTH
import dev.butter.mathbot.gui.frame.PANEL_BACKGROUND
import dev.butter.mathbot.gui.label.FilePathDisplay
import dev.butter.mathbot.gui.label.PathLabel
import dev.butter.mathbot.module.Addon
import javax.inject.Inject
import javax.swing.JPanel

const val DISTANCE_FROM_TOP = 25

class FilePanel
@Inject
constructor(
    private val pathLabel: PathLabel,
    private val filePathDisplay: FilePathDisplay,
    private val selectPathButton: SelectPathButton,
) : JPanel(), Addon {
    override fun init() {
        setLocation(0, 0)
        setSize(GUI_WIDTH, FILE_PANEL_HEIGHT)
        layout = null
        add(pathLabel)
        add(filePathDisplay)
        add(selectPathButton)
        background = PANEL_BACKGROUND
    }
}