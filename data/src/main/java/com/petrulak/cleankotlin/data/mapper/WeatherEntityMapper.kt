package com.petrulak.cleankotlin.data.mapper

import com.petrulak.cleankotlin.data.mapper.base.Mapper
import com.petrulak.cleankotlin.data.source.local.model.WeatherEntity
import com.petrulak.cleankotlin.domain.model.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherEntityMapper
@Inject
constructor() : Mapper<Weather, WeatherEntity>() {

    override fun reverse(input: Weather): WeatherEntity {

        val id = input.id
        val name = input.name
        val visibility = input.visibility

        return WeatherEntity(id, name, visibility)
    }

    override fun map(input: WeatherEntity): Weather {
        return Weather(input.uid, input.name, input.visibility)
    }
}