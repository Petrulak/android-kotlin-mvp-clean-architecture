package com.petrulak.cleankotlin.ui.example2

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.platform.extensions.getDisposableSingleObserver
import com.petrulak.cleankotlin.ui.base.BasePresenterImpl
import com.petrulak.cleankotlin.ui.example2.Example2Contract.View
import java.util.*
import javax.inject.Inject


@ViewScope
class Example2Presenter
@Inject
constructor(private val getWeatherRemotelyUseCase: GetWeatherRemotelyUseCase) : BasePresenterImpl(), Example2Contract.Presenter {

    private var view: View? = null

    override fun attachView(view: View) {
        this.view = checkNotNull(view)
    }

    override fun start() {
        super.start()
        refresh(CITY)
    }

    override fun refresh(city: String) {
        val disposable = getWeatherRemotelyUseCase
            .execute(city)
            .subscribeWith(getDisposableSingleObserver({ onWeatherSuccess(it) }, { onWeatherError(it) }))
        disposables.add(disposable)
    }

    private fun onWeatherSuccess(item: Weather) {
        val modified = item.copy(visibility = Random().nextInt(80 - 65) + 65)
        view?.showWeather(modified)
    }

    private fun onWeatherError(t: Throwable) {
        //no need to log this exception, it is already logged on the Interactor level.
    }

    companion object {
        val CITY = "London,uk"
    }
}