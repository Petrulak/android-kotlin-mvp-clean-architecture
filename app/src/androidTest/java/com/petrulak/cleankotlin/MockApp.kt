package com.petrulak.cleankotlin

import com.petrulak.cleankotlin.di.component.ApplicationComponent
import com.petrulak.cleankotlin.di.component.DaggerApplicationMockComponent
import com.petrulak.cleankotlin.di.module.ApplicationMockModule
import com.petrulak.cleankotlin.di.module.InteractorMockModule

class MockApp : App() {

    override fun initializeAppComponent(): ApplicationComponent {
        return DaggerApplicationMockComponent.builder()
            .applicationMockModule(ApplicationMockModule(this))
            .interactorMockModule(InteractorMockModule())
            .build()
    }
}
