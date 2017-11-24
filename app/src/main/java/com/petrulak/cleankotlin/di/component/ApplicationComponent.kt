package com.petrulak.cleankotlin.di.component

import android.content.Context
import com.petrulak.cleankotlin.di.module.*
import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherLocallyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherUseCase
import com.petrulak.cleankotlin.platform.analytics.AnalyticsManager
import com.petrulak.cleankotlin.platform.bus.data.DataBus
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import com.petrulak.cleankotlin.platform.navigation.Navigator
import com.petrulak.cleankotlin.ui.base.BaseActivity
import com.petrulak.cleankotlin.ui.base.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    DataModule::class,
    MapperModule::class,
    NetworkModule::class,
    InteractorModule::class,
    ApplicationModule::class))
interface ApplicationComponent {

    fun inject(item: BaseActivity)
    fun inject(item: BaseFragment)

    /* exposing to other components [com.petrulak.cleankotlin.di.component.ViewComponent] */
    fun context(): Context

    fun scheduler(): SchedulerProvider
    fun navigator(): Navigator
    fun eventBus(): EventBus
    fun dataBus(): DataBus
    fun analyticsManager(): AnalyticsManager
    fun getWeatherUseCase(): GetWeatherUseCase
    fun getWeatherRemotelyUseCase(): GetWeatherRemotelyUseCase
    fun getWeatherLocallyUseCase(): GetWeatherLocallyUseCase
}
