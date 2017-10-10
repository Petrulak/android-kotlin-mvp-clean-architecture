package com.petrulak.cleankotlin.ui.example1.fragment

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.GetWeatherUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Contract.View
import dagger.internal.Preconditions.checkNotNull
import timber.log.Timber
import javax.inject.Inject

@ViewScope
class Example1Presenter
@Inject
constructor(
    private val getWeather: GetWeatherUseCase) : Example1Contract.Presenter {

    private var view: View? = null

    override fun attachView(view: View) {
        this.view = checkNotNull(view)
    }

    override fun start() {
        refresh()
    }

    override fun stop() {
        getWeather.dispose()
    }

    override fun refresh() {
        getWeather.dispose() //clear older disposables
        getWeather.execute({ onWeatherSuccess(it) }, { Timber.e(it) }, "London,uk")
        /* In case you are not interested in errors, use: getWeather.execute({ view?.showWeather(it) }, params = "London,uk") */
    }

    private fun onWeatherSuccess(item: Weather) {
        Timber.e("visibility ${item.visibility}")
        view?.showWeather(item)
    }
}
