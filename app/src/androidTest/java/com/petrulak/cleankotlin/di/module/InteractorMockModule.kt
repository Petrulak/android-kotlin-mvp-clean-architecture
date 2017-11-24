package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherLocallyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class InteractorMockModule {

    @Singleton
    @Provides
    internal fun getWeatherRemotelyUseCase(): GetWeatherRemotelyUseCase {
        return Mockito.mock(GetWeatherRemotelyUseCase::class.java)
    }

    @Singleton
    @Provides
    internal fun getWeatherLocallyUseCase(): GetWeatherLocallyUseCase {
        return Mockito.mock(GetWeatherLocallyUseCase::class.java)
    }

    @Singleton
    @Provides
    internal fun getWeatherUseCase(): GetWeatherUseCase {
        return Mockito.mock(GetWeatherUseCase::class.java)
    }
}