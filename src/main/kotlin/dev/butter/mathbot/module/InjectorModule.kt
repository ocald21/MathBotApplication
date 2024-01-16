package dev.butter.mathbot.module

import com.google.inject.Guice
import com.google.inject.Injector
import dev.butter.mathbot.gui.GuiModule

class InjectorModule : BaseModule() {
    override fun configure() {
        listOf(
            GuiModule(),
        ).forEach(::install)
    }

    fun createInjector(): Injector = Guice.createInjector(this)
}