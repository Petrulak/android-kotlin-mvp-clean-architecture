package com.petrulak.cleankotlin.ui.example1.fragment

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.WeatherLocalUseCase
import com.petrulak.cleankotlin.domain.interactor.WeatherRemoteUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Contract.View
import dagger.internal.Preconditions.checkNotNull
import javax.inject.Inject

@ViewScope
class Example1Presenter
@Inject
constructor(
    private val weatherLocal: WeatherLocalUseCase,
    private val weatherRemote: WeatherRemoteUseCase) : Example1Contract.Presenter {

    private var view: View? = null

    override fun attachView(view: View) {
        this.view = checkNotNull(view)
    }

    override fun start() {
        weatherLocal.execute({ onWeatherSuccess(it) }, { onWeatherError(it) }, "London")
        refresh()
    }

    override fun stop() {
        weatherLocal.dispose()
        weatherRemote.dispose()
    }

    override fun refresh() {
        weatherRemote.executeAndPersist("London,uk")
    }

    private fun onWeatherSuccess(weather: Weather) {
        view?.showWeather(weather)
    }

    private fun onWeatherError(t: Throwable) {

    }
}
