package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.interactor.GetWeatherLocallyUseCase
import com.petrulak.cleankotlin.domain.interactor.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.interactor.GetWeatherUseCase
import com.petrulak.cleankotlin.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class InteractorModule {

    @Singleton
    @Provides
    internal fun getWeatherRemotelyUseCase(schedulerProvider: SchedulerProvider, repository: WeatherRepository): GetWeatherRemotelyUseCase {
        return GetWeatherRemotelyUseCase(schedulerProvider, repository)
    }

    @Singleton
    @Provides
    internal fun getWeatherLocallyUseCase(schedulerProvider: SchedulerProvider, repository: WeatherRepository): GetWeatherLocallyUseCase {
        return GetWeatherLocallyUseCase(schedulerProvider, repository)
    }

    @Singleton
    @Provides
    internal fun getWeatherUseCase(schedulerProvider: SchedulerProvider, repository: WeatherRepository): GetWeatherUseCase {
        return GetWeatherUseCase(schedulerProvider, repository)
    }
}
