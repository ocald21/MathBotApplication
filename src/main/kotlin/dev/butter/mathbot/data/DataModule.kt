package dev.butter.mathbot.data

import dev.butter.mathbot.module.BaseModule

class DataModule : BaseModule() {
    override fun configure() =
        addBinding(
            LogLineProcessor::class,
            EquationProcessor::class,
        )
}