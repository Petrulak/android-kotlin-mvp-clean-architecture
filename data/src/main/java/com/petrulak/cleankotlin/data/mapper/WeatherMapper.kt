package com.petrulak.cleankotlin.data.mapper

import com.petrulak.cleankotlin.data.mapper.base.Mapper
import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto
import com.petrulak.cleankotlin.domain.model.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherMapper
@Inject
constructor() : Mapper<Weather, WeatherDto>() {

    override fun map(input: WeatherDto): Weather {

        val id = input.id ?: invalidLong
        val name = input.name ?: emptyString
        val visibility = input.visibility ?: invalidInt

        return Weather(id, name, visibility)
    }

    override fun reverse(input: Weather): WeatherDto {
        return WeatherDto(input.id, input.name, input.visibility)
    }
}
