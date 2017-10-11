package com.petrulak.cleankotlin.data.mapper

import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto
import com.petrulak.cleankotlin.domain.model.Weather
import org.junit.Assert
import org.junit.Test

class WeatherMapperTest {

    val mapper = WeatherMapper()

    @Test
    fun mapTest_shouldMapNullValuesToWeather() {
        val weatherDto = WeatherDto(null, null, null)
        val weather = mapper.map(weatherDto)
        Assert.assertEquals(weather.id, mapper.invalidLong)
        Assert.assertEquals(weather.name, mapper.emptyString)
        Assert.assertEquals(weather.visibility, mapper.invalidInt)
    }

    @Test
    fun mapTest_shouldMapToWeather() {
        val weatherDto = WeatherDto(30L, "Berlin", 123)
        val weather = mapper.map(weatherDto)
        Assert.assertEquals(weather.id, 30L)
        Assert.assertEquals(weather.name, "Berlin")
        Assert.assertEquals(weather.visibility, 123)
    }

    @Test
    fun reverseTest_shouldMapToWeatherDto() {
        val weather = Weather(30L, "Berlin", 123)
        val weatherDto = mapper.reverse(weather)
        Assert.assertEquals(weatherDto.id, 30L)
        Assert.assertEquals(weatherDto.name, "Berlin")
        Assert.assertEquals(weatherDto.visibility, 123)
    }

    @Test
    fun mapTest_emptyList() {
        val mappedList = mapper.map(emptyList())
        Assert.assertEquals(mappedList.size, 0)
    }

    @Test
    fun mapTest_notEmptyList() {
        val weather = WeatherDto(30L, "Berlin", 123)
        val mappedList = mapper.map(listOf(weather, weather, weather, weather))
        Assert.assertEquals(mappedList.size, 4)
        Assert.assertEquals(mappedList[0].id, 30L)
        Assert.assertEquals(mappedList[0].name, "Berlin")
        Assert.assertEquals(mappedList[0].visibility, 123)
    }

}