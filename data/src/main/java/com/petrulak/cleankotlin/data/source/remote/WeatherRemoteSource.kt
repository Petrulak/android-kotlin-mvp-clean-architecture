package com.petrulak.cleankotlin.data.source.remote


import com.petrulak.cleankotlin.data.source.RemoteSource
import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRemoteSource
@Inject
constructor(retrofit: Retrofit) : RemoteSource {

    private val client: WeatherApiClient = retrofit.create(WeatherApiClient::class.java)

    override fun getWeatherForCity(city: String): Single<WeatherDto> {
        return client.getWeatherForCity(city)
    }

}
