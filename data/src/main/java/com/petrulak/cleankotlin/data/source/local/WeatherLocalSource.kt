package com.petrulak.cleankotlin.data.source.local


import com.petrulak.cleankotlin.data.mapper.WeatherEntityMapper
import com.petrulak.cleankotlin.data.source.LocalSource
import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalSource
@Inject
constructor(private val db: WeatherDatabase,
            private val mapper: WeatherEntityMapper,
            private val schedulers: SchedulerProviderI) : LocalSource {

    override fun save(weather: Weather) {
        val item = mapper.map(weather)

        Single.fromCallable {
            db.weatherDao().deleteWeatherById(item.uid)
            db.weatherDao().insert(item)
        }.subscribeOn(schedulers.io()).subscribe()
    }

    override fun getWeatherForCity(name: String): Flowable<Weather> {
        return db.weatherDao()
            .getByName(name)
            .map { mapper.reverse(it) }
    }
}
