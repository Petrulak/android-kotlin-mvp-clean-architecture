package com.petrulak.cleankotlin.di.component

import android.content.Context
import com.petrulak.cleankotlin.di.module.*
import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import com.petrulak.cleankotlin.domain.interactor.WeatherLocalUseCase
import com.petrulak.cleankotlin.domain.interactor.WeatherRemoteUseCase
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

    //exposing to other(ViewComponent) components
    fun context(): Context
    fun scheduler(): SchedulerProviderI
    fun navigator(): Navigator
    fun eventBus(): EventBus
    fun dataBus(): DataBus
    fun analyticsManager(): AnalyticsManager
    fun weatherUseCase(): WeatherRemoteUseCase
    fun WeatherPersistenceUseCase(): WeatherLocalUseCase
}
