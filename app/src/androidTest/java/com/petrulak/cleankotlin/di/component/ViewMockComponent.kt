package com.petrulak.cleankotlin.di.component

import com.petrulak.cleankotlin.di.module.PresenterMockModule
import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.ui.example2.Example2ActivityTest
import com.petrulak.cleankotlin.ui.example2.Example2PresenterTest
import dagger.Component

@ViewScope
@Component(
    dependencies = arrayOf(
        ApplicationMockComponent::class
    ),
    modules = arrayOf(PresenterMockModule::class)
)
interface ViewMockComponent {

    fun inject(item: Example2ActivityTest)
    fun inject(item: Example2PresenterTest)
}