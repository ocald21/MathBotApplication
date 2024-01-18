package dev.butter.mathbot.file

import dev.butter.mathbot.module.BaseModule

class FileModule : BaseModule() {
    override fun configure() =
        addBinding(
            FileTracker::class,
        )
}