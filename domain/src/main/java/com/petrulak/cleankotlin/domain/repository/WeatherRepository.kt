package com.petrulak.cleankotlin.domain.repository

import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WeatherRepository {
    fun getWeatherForCity(city: String): Flowable<Weather>
    fun getWeatherForCityRemotely(city: String): Single<Weather>
    fun getWeatherForCityLocally(name: String): Flowable<Weather>
    fun save(weather: Weather): Completable
}
