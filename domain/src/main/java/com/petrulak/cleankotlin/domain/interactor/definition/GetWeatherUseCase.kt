package com.petrulak.cleankotlin.domain.interactor.definition

import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Flowable

interface GetWeatherUseCase {

    fun execute(city: String): Flowable<Weather>
}