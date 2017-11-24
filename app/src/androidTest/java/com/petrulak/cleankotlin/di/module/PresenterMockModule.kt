package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.ui.example2.Example2Contract
import com.petrulak.cleankotlin.ui.example2.Example2Presenter
import dagger.Module
import dagger.Provides

@Module
class PresenterMockModule {

    @ViewScope
    @Provides
    internal fun example2Presenter(remotelyUseCaseGet: GetWeatherRemotelyUseCase): Example2Contract.Presenter {
        return Example2Presenter(remotelyUseCaseGet)
    }

}