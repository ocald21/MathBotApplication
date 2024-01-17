package dev.butter.mathbot.listener

import dev.butter.mathbot.ApplicationState.STOPPED
import dev.butter.mathbot.applicationState
import dev.butter.mathbot.config
import dev.butter.mathbot.file.save
import dev.butter.mathbot.module.Addon
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

class ExitListener : WindowListener, Addon {
    override fun init() = Unit
    override fun windowOpened(e: WindowEvent) = Unit
    override fun windowClosing(e: WindowEvent) {
        config.save()
        applicationState = STOPPED
    }
    override fun windowClosed(e: WindowEvent) = Unit
    override fun windowIconified(e: WindowEvent) = Unit
    override fun windowDeiconified(e: WindowEvent) = Unit
    override fun windowActivated(e: WindowEvent) = Unit
    override fun windowDeactivated(e: WindowEvent) = Unit
}