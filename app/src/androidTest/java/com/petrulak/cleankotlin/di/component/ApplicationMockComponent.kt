package com.petrulak.cleankotlin.di.component

import com.petrulak.cleankotlin.di.module.ApplicationMockModule
import com.petrulak.cleankotlin.di.module.InteractorMockModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    ApplicationMockModule::class,
    InteractorMockModule::class))
interface ApplicationMockComponent : ApplicationComponent {

}