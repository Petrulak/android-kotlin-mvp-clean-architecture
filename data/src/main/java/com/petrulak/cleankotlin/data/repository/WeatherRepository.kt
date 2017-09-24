package com.petrulak.cleankotlin.data.repository

import com.petrulak.cleankotlin.data.mapper.base.Mapper
import com.petrulak.cleankotlin.data.source.LocalSource
import com.petrulak.cleankotlin.data.source.RemoteSource
import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.domain.repository.WeatherRepositoryI
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository
@Inject
constructor(private val weatherSource: RemoteSource,
            private val weatherSourceLocal: LocalSource,
            private val weatherMapper: Mapper<Weather, WeatherDto>) : WeatherRepositoryI {

    override fun getWeatherForCityRemotely(city: String): Single<Weather> {
        return weatherSource
            .getWeatherForCity(city)
            .map { weatherMapper.map(it) }
    }

    override fun save(weather: Weather) {
        weatherSourceLocal.save(weather)
    }

    override fun getWeatherForCityLocally(name: String): Flowable<Weather> {
        return weatherSourceLocal.getWeatherForCity(name)
    }
}
