package dev.butter.mathbot.file

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    var logFilePath: String = "",
)