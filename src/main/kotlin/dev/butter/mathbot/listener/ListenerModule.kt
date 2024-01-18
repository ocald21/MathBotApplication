package dev.butter.mathbot.listener

import dev.butter.mathbot.module.BaseModule

class ListenerModule : BaseModule() {
    override fun configure() =
        addBinding(
            ButtonMouseListener::class,
            ExitListener::class,
        )
}