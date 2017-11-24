package com.petrulak.cleankotlin.domain.interactor.definition

import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Single

interface GetWeatherRemotelyUseCase {

    fun execute(city: String): Single<Weather>
}