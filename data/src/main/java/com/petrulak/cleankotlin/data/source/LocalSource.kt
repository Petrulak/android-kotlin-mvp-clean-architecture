package com.petrulak.cleankotlin.data.source

import com.petrulak.cleankotlin.data.source.local.model.WeatherEntity
import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Completable
import io.reactivex.Flowable

interface LocalSource {
    fun getWeatherForCity(name: String): Flowable<WeatherEntity>
    fun save(weather: Weather): Completable
}
