package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.WeatherLocalUseCase
import com.petrulak.cleankotlin.domain.interactor.WeatherRemoteUseCase
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
    internal fun example1Presenter(localUseCase: WeatherLocalUseCase, remoteUseCase: WeatherRemoteUseCase): Example1Contract.Presenter {
        return Example1Presenter(localUseCase, remoteUseCase)
    }

    @ViewScope
    @Provides
    internal fun example2Presenter(remoteUseCase: WeatherRemoteUseCase): Example2Contract.Presenter {
        return Example2Presenter(remoteUseCase)
    }

    @ViewScope
    @Provides
    internal fun example3Presenter(remoteUseCase: WeatherRemoteUseCase, dataBus: DataBus, eventBus: EventBus): Example3Contract.Presenter {
        return Example3Presenter(remoteUseCase, dataBus, eventBus)
    }

}
