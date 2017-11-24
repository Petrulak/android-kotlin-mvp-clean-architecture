package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.interactor.GetWeatherLocallyUseCaseImpl
import com.petrulak.cleankotlin.domain.interactor.GetWeatherRemotelyUseCaseImpl
import com.petrulak.cleankotlin.domain.interactor.GetWeatherUseCaseImpl
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherLocallyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherUseCase
import com.petrulak.cleankotlin.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class InteractorModule {

    @Singleton
    @Provides
    internal fun getWeatherRemotelyUseCase(schedulerProvider: SchedulerProvider, repository: WeatherRepository): GetWeatherRemotelyUseCase {
        return GetWeatherRemotelyUseCaseImpl(schedulerProvider, repository)
    }

    @Singleton
    @Provides
    internal fun getWeatherLocallyUseCase(schedulerProvider: SchedulerProvider, repository: WeatherRepository): GetWeatherLocallyUseCase {
        return GetWeatherLocallyUseCaseImpl(schedulerProvider, repository)
    }

    @Singleton
    @Provides
    internal fun getWeatherUseCase(schedulerProvider: SchedulerProvider, repository: WeatherRepository): GetWeatherUseCase {
        return GetWeatherUseCaseImpl(schedulerProvider, repository)
    }
}
