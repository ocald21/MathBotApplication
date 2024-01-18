package dev.butter.mathbot.module

import com.google.inject.Guice
import com.google.inject.Injector
import dev.butter.mathbot.data.DataModule
import dev.butter.mathbot.file.FileModule
import dev.butter.mathbot.gui.GuiModule
import dev.butter.mathbot.listener.ListenerModule

class InjectorModule : BaseModule() {
    override fun configure() {
        listOf(
            DataModule(),
            FileModule(),
            GuiModule(),
            ListenerModule(),
        ).forEach(::install)
    }

    fun createInjector(): Injector = Guice.createInjector(this)
}