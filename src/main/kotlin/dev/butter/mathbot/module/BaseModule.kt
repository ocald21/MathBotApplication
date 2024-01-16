package dev.butter.mathbot.module

import com.authzee.kotlinguice4.KotlinModule
import com.authzee.kotlinguice4.multibindings.KotlinMultibinder
import kotlin.reflect.KClass

abstract class BaseModule : KotlinModule() {
    private lateinit var addonBinder: KotlinMultibinder<Addon>

    protected fun addBinding(vararg clazz: KClass<out Addon>) {
        addonBinder = KotlinMultibinder.newSetBinder(kotlinBinder)

        clazz.forEach { addonBinder.addBinding().to(it.java) }
    }
}