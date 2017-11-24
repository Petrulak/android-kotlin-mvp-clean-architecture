package com.petrulak.cleankotlin.domain.interactor

import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherRemotelyUseCaseImpl
@Inject
constructor(private val schedulerProvider: SchedulerProvider,
            private val repository: WeatherRepository) : GetWeatherRemotelyUseCase {

    private fun buildUseCase(city: String): Single<Weather> {
        return when (city.isEmpty()) {
            true  -> Single.error(IllegalArgumentException("Wrong parameters"))
            false -> repository.getWeatherForCityRemotely(city)
        }
    }

    override fun execute(city: String): Single<Weather> {
        return buildUseCase(city).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }

}
