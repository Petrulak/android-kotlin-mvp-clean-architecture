package com.petrulak.cleankotlin.ui.example1.fragment

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.platform.extensions.getDisposableSubscriber
import com.petrulak.cleankotlin.ui.base.BasePresenterImpl
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Contract.View
import dagger.internal.Preconditions.checkNotNull
import timber.log.Timber
import javax.inject.Inject

@ViewScope
class Example1Presenter
@Inject
constructor(
    private val getWeather: GetWeatherUseCase) : BasePresenterImpl(), Example1Contract.Presenter {

    private var view: View? = null

    override fun attachView(view: View) {
        this.view = checkNotNull(view)
    }

    override fun start() {
        refresh()
    }

    override fun refresh() {
        val disposable = getWeather
            .execute("London,uk")
            .subscribeWith(getDisposableSubscriber({ onWeatherSuccess(it) }, { Timber.e(it) }))
        disposables.add(disposable)
    }

    private fun onWeatherSuccess(item: Weather) {
        view?.showWeather(item)
    }
}
