package com.petrulak.cleankotlin.di.component

import com.petrulak.cleankotlin.di.module.PresenterModule
import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.ui.example1.Example1Activity
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Fragment
import com.petrulak.cleankotlin.ui.example2.Example2Activity
import com.petrulak.cleankotlin.ui.example3.Example3Activity
import com.petrulak.cleankotlin.ui.example3.fragment.Example3Fragment
import com.petrulak.cleankotlin.ui.main.MainActivity
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(
    ApplicationComponent::class),
    modules = arrayOf(PresenterModule::class))
interface ViewComponent {
    fun inject(item: MainActivity)
    fun inject(item: Example1Activity)
    fun inject(item: Example1Fragment)
    fun inject(item: Example2Activity)
    fun inject(item: Example3Fragment)
    fun inject(item: Example3Activity)
}
