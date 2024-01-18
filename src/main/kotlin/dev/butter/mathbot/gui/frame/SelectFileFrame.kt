package dev.butter.mathbot.gui.frame

import dev.butter.mathbot.config
import dev.butter.mathbot.gui.label.FilePathDisplay
import dev.butter.mathbot.module.Addon
import javax.inject.Inject
import javax.swing.JFileChooser
import javax.swing.JFileChooser.*
import javax.swing.JFrame

class SelectFileFrame
@Inject
constructor(
    private val filePathDisplay: FilePathDisplay,
) : JFrame(), Addon {
    override fun init() {
        val jFileChooser = JFileChooser(config.data.logFilePath)
            .apply { fileSelectionMode = DIRECTORIES_ONLY }
        val result = jFileChooser.showOpenDialog(this)

        if (result == APPROVE_OPTION) {
            val selectedFile = jFileChooser.selectedFile
            println("Selected file: $selectedFile")
            config.data.logFilePath = selectedFile.absolutePath
            filePathDisplay.text = selectedFile.absolutePath
        } else {
            println("Selection canceled.")
        }

        dispose()
    }
}