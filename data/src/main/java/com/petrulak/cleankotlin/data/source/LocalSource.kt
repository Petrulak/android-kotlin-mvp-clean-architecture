package com.petrulak.cleankotlin.data.source

import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Flowable

interface LocalSource {
    fun getWeatherForCity(name: String): Flowable<Weather>
    fun save(weather: Weather)
}
