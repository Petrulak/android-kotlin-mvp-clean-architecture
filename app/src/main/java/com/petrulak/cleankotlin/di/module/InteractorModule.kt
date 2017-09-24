package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import com.petrulak.cleankotlin.domain.interactor.WeatherLocalUseCase
import com.petrulak.cleankotlin.domain.interactor.WeatherRemoteUseCase
import com.petrulak.cleankotlin.domain.repository.WeatherRepositoryI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class InteractorModule {


    @Singleton
    @Provides
    internal fun weatherUseCase(schedulerProvider: SchedulerProviderI, repository: WeatherRepositoryI): WeatherRemoteUseCase {
        return WeatherRemoteUseCase(schedulerProvider, repository)
    }

    @Singleton
    @Provides
    internal fun weatherPersistenceUseCase(schedulerProvider: SchedulerProviderI, repository: WeatherRepositoryI): WeatherLocalUseCase {
        return WeatherLocalUseCase(schedulerProvider, repository)
    }
}
