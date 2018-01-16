package com.petrulak.cleankotlin.domain.interactor

import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherLocallyUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.domain.repository.WeatherRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherLocallyUseCaseImpl
@Inject
constructor(
    private val schedulerProvider: SchedulerProvider,
    private val repository: WeatherRepository
) : GetWeatherLocallyUseCase {

    private fun buildUseCase(city: String): Flowable<Weather> {
        return when (city.isEmpty()) {
            true  -> Flowable.error(IllegalArgumentException("Wrong parameters"))
            false -> repository.getWeatherForCityLocally(city)
        }
    }

    override fun execute(city: String): Flowable<Weather> {
        return buildUseCase(city).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }
}