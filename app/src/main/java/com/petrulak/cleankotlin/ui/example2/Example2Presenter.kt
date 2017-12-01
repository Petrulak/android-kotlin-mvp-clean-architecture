package com.petrulak.cleankotlin.ui.example2

import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.platform.extensions.getDisposableSingleObserver
import com.petrulak.cleankotlin.ui.base.BasePresenterImpl
import com.petrulak.cleankotlin.ui.example2.Example2Contract.View
import javax.inject.Inject


@ViewScope
class Example2Presenter
@Inject
constructor(private val getWeatherRemotelyUseCase: GetWeatherRemotelyUseCase) : BasePresenterImpl(), Example2Contract.Presenter {

     var view: View? = null

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
            .doOnSubscribe { setViewState(ViewState.Loading()) }
            .doFinally { setViewState(ViewState.LoadingFinished()) }
            .subscribeWith(getDisposableSingleObserver({ setViewState(ViewState.Success(it)) }, { setViewState(ViewState.Error(it)) }))
        disposables.add(disposable)
    }

    private fun setViewState(state: ViewState) {
        view?.viewState = state
    }

    companion object {
        val CITY = "London,uk"
    }
}