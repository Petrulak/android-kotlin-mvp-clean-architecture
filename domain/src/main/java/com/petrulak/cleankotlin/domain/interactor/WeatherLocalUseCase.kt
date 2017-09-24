package com.petrulak.cleankotlin.domain.interactor

import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import com.petrulak.cleankotlin.domain.interactor.base.FlowableInteractor
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.domain.repository.WeatherRepositoryI
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalUseCase
@Inject
constructor(schedulerProvider: SchedulerProviderI,
            private val repository: WeatherRepositoryI) : FlowableInteractor<Weather, String>(schedulerProvider) {

    override fun buildUseCase(city: String): Flowable<Weather> {
        return when (city.isEmpty()) {
            true  -> Flowable.error(IllegalArgumentException("Wrong parameters"))
            false -> repository.getWeatherForCityLocally(city)
        }
    }

}