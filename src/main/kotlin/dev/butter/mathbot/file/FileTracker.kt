package dev.butter.mathbot.file

import dev.butter.mathbot.ApplicationState.STARTING
import dev.butter.mathbot.applicationState
import dev.butter.mathbot.config
import dev.butter.mathbot.data.LogLineProcessor
import dev.butter.mathbot.module.Addon
import java.io.BufferedReader
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.Delegates

@Singleton
class FileTracker
@Inject
constructor(
    private val logLineProcessor: LogLineProcessor,
) : Runnable, Addon {
    private lateinit var file: File
    private lateinit var reader: BufferedReader
    private var latestLine by Delegates.notNull<Int>()

    override fun init() {
        validateFile()
    }

    override fun run() {
        if (!validateFile()) {
            return
        }

        if (applicationState == STARTING) {
            val initialLines = reader.readLines()

            newReader()

            logLineProcessor.processStart(initialLines)

            return updateLatestLine()
        }

        val newLineCount = getLineCount()

        if (newLineCount <= latestLine) {
            return
        }

        val newLines = reader.readLines().drop(latestLine)

        logLineProcessor.processNew(newLines)

        newReader()

        latestLine = newLineCount
    }

    fun close() = reader.close()

    private fun validateFile(): Boolean {
        val file = File("${config.data.logFilePath}/latest.log")

        if (!file.exists() ||
            !file.isFile
        ) {
            return false
        }

        if (!this::file.isInitialized ||
            !this::reader.isInitialized ||
            file.path != this.file.path
        ) {
            this.file = file
            this.reader = this.file.bufferedReader()
        }

        return true
    }

    private fun updateLatestLine() {
        latestLine = getLineCount()
    }

    private fun getLineCount(): Int {
        var lineCount = 0

        reader.use { reader ->
            while (reader.readLine() != null) {
                lineCount++
            }
        }

        newReader()

        return lineCount
    }

    private fun newReader() {
        reader.close()
        reader = file.bufferedReader()
    }
}