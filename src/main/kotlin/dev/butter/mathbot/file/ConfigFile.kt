package dev.butter.mathbot.file

import kotlinx.serialization.encodeToString
import java.io.File
import java.io.FileWriter
import java.nio.charset.Charset

class ConfigFile<T>(
    val file: File,
    val defaultValue: () -> T,
) {
    var data: T = defaultValue()
}

inline fun <reified T> ConfigFile<T>.load() = this
    .file
    .inputStream()
    .readBytes()
    .toString(Charset.defaultCharset())
    .let { contents ->
        if (contents.isNotEmpty()) {
            try {
                this.data = json.decodeFromString<T>(contents)
            } catch (e: Exception) {
                this.data = this.defaultValue()
                save()
            }
        }
    }

inline fun <reified T> ConfigFile<T>.save() {
    FileWriter(this.file, false).apply {
        write(json.encodeToString(data))
        close()
    }
}