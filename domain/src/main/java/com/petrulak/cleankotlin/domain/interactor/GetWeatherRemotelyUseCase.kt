package com.petrulak.cleankotlin.domain.interactor

import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.interactor.base.SingleInteractor
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherRemotelyUseCase
@Inject
constructor(schedulerProvider: SchedulerProvider,
            private val repository: WeatherRepository) : SingleInteractor<Weather, String>(schedulerProvider) {

    override fun buildUseCase(city: String): Single<Weather> {
        return when (city.isEmpty()) {
            true  -> Single.error(IllegalArgumentException("Wrong parameters"))
            false -> repository.getWeatherForCityRemotely(city)
        }
    }
}
