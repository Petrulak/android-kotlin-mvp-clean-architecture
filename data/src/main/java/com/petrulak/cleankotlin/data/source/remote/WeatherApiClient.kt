package com.petrulak.cleankotlin.data.source.remote

import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiClient {
    @GET("data/2.5/weather")
    fun getWeatherForCity(@Query("q") city: String): Single<WeatherDto>
}