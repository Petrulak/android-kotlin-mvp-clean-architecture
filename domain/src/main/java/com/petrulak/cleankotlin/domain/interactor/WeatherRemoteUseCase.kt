package com.petrulak.cleankotlin.domain.interactor

import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import com.petrulak.cleankotlin.domain.interactor.base.SingleInteractor
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.domain.repository.WeatherRepositoryI
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRemoteUseCase
@Inject
constructor(schedulerProvider: SchedulerProviderI,
            private val repository: WeatherRepositoryI) : SingleInteractor<Weather, String>(schedulerProvider) {

    override fun buildUseCase(city: String): Single<Weather> {
        return when (city.isEmpty()) {
            true  -> Single.error(IllegalArgumentException("Wrong parameters"))
            false -> repository.getWeatherForCityRemotely(city)
        }
    }

    fun executeAndPersist(id: String) {
        execute({ doOnNext(it) }, { doOnError(it) }, id)
    }

    private fun doOnNext(item: Weather) {
        //Changing visibility value manually to see changes in the UI
        val modified = Weather(item.id, item.name, Random().nextInt(80 - 65) + 65)
        repository.save(modified)
    }

    private fun doOnError(e: Throwable) {
    }
}
