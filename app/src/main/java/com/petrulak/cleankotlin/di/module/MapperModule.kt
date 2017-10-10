package com.petrulak.cleankotlin.di.module

import com.petrulak.cleankotlin.data.mapper.WeatherEntityMapper
import com.petrulak.cleankotlin.data.mapper.WeatherMapper
import com.petrulak.cleankotlin.data.mapper.base.Mapper
import com.petrulak.cleankotlin.data.source.local.model.WeatherEntity
import com.petrulak.cleankotlin.data.source.remote.model.WeatherDto
import com.petrulak.cleankotlin.domain.model.Weather
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Singleton
    @Provides
    internal fun weatherMapper(mapper: WeatherMapper): Mapper<Weather, WeatherDto> {
        return mapper
    }

    @Singleton
    @Provides
    internal fun weatherEntityMapper(mapper: WeatherEntityMapper): Mapper<Weather, WeatherEntity> {
        return mapper
    }

}
