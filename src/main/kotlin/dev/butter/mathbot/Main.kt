package dev.butter.mathbot

import com.authzee.kotlinguice4.getInstance
import dev.butter.mathbot.ApplicationState.*
import dev.butter.mathbot.file.ConfigFile
import dev.butter.mathbot.file.FileTracker
import dev.butter.mathbot.file.UserData
import dev.butter.mathbot.file.load
import dev.butter.mathbot.module.AddonLoader
import dev.butter.mathbot.module.InjectorModule
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

val configDirectory: File = File("${System.getProperty("user.dir")}/config")
val configFile: File = File("${configDirectory.absolutePath}/config.json")
val config = ConfigFile(configFile, ::UserData)
var applicationState = STARTING

suspend fun main(): Unit = coroutineScope {
    launch {
        initConfig()

        val injector = InjectorModule()
            .createInjector()
        val tracker = injector
            .getInstance<FileTracker>()
            .apply(FileTracker::run)
        val addonLoader = injector
            .getInstance<AddonLoader>()
            .apply(AddonLoader::init)

        applicationState = RUNNING

        while(true) {
            delay(1000)
            tracker.run()

            if (applicationState == STOPPED) {
                tracker.close()
                break
            }
        }
    }
}

fun initConfig() {
    if (!configDirectory.exists()) {
        configDirectory.mkdir()
    }

    if (!configFile.exists()) {
        configFile.createNewFile()
    }

    config.load()
}

enum class ApplicationState {
    STARTING,
    RUNNING,
    STOPPED,
    ;
}