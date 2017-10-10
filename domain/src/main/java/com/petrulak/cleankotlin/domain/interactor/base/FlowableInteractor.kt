package com.petrulak.cleankotlin.domain.interactor.base

import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import io.reactivex.Flowable

abstract class FlowableInteractor<T, Parameters>(
    private val schedulerProvider: SchedulerProvider) : BaseInteractor<T>() {

    abstract fun buildUseCase(parameters: Parameters): Flowable<T>

    fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit = {}, params: Parameters) {
        val flowable = buildUseCase(params).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
        val disposable = flowable.subscribeWith(getDisposableSubscriber(onNext, onError))
        disposables.add(disposable)
    }

}
