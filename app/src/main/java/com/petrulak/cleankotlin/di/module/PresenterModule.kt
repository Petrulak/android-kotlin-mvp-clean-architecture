package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherUseCase
import com.petrulak.cleankotlin.platform.bus.data.DataBus
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Contract
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Presenter
import com.petrulak.cleankotlin.ui.example2.Example2Contract
import com.petrulak.cleankotlin.ui.example2.Example2Presenter
import com.petrulak.cleankotlin.ui.example3.fragment.Example3Contract
import com.petrulak.cleankotlin.ui.example3.fragment.Example3Presenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @ViewScope
    @Provides
    internal fun example1Presenter(getWeatherUseCase: GetWeatherUseCase): Example1Contract.Presenter {
        return Example1Presenter(getWeatherUseCase)
    }

    @ViewScope
    @Provides
    internal fun example2Presenter(remotelyUseCaseGet: GetWeatherRemotelyUseCase): Example2Contract.Presenter {
        return Example2Presenter(remotelyUseCaseGet)
    }

    @ViewScope
    @Provides
    internal fun example3Presenter(remotelyUseCaseGet: GetWeatherRemotelyUseCase, dataBus: DataBus, eventBus: EventBus): Example3Contract.Presenter {
        return Example3Presenter(remotelyUseCaseGet, dataBus, eventBus)
    }

}
