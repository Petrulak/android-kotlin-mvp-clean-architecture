package com.petrulak.cleankotlin.domain.repository

import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Flowable
import io.reactivex.Single

interface WeatherRepositoryI {
    fun getWeatherForCityRemotely(city: String): Single<Weather>
    fun getWeatherForCityLocally(name: String): Flowable<Weather>
    fun save(weather: Weather)
}
