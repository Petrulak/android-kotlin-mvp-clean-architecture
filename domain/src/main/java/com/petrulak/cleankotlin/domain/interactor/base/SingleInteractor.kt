package com.petrulak.cleankotlin.domain.interactor.base

import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import io.reactivex.Single

abstract class SingleInteractor<T, Parameters>(
    private var schedulerProvider: SchedulerProviderI) : BaseInteractor<T>() {

    abstract fun buildUseCase(parameters: Parameters): Single<T>

    fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit, params: Parameters) {
        val single = buildUseCase(params).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
        val disposable = single.subscribeWith(getDisposableSingleObserver(onNext, onError))
        disposables.add(disposable)
    }

}
