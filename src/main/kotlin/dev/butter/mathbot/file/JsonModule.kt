package dev.butter.mathbot.file

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

val json = Json {
    prettyPrint = true
    allowStructuredMapKeys = true
    isLenient = true

    serializersModule = SerializersModule {

    }
}