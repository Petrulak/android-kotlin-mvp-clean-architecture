package com.petrulak.cleankotlin.data.source

import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto

import io.reactivex.Single

interface RemoteSource {
    fun getWeatherForCity(city: String): Single<WeatherDto>
}
