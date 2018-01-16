package com.petrulak.cleankotlin.domain.interactor.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber


/**
 * If you have set up Crashlytics and/or Instabug in [com.petrulak.cleankotlin.platform.logging.ErrorReportingTree]
 * all the errors will be pushed to that platform.
 */
abstract class BaseInteractor<T> {

    protected var disposables: CompositeDisposable = CompositeDisposable()

    protected fun getDisposableSingleObserver(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit = {}
    ): DisposableSingleObserver<T> {

        return object : DisposableSingleObserver<T>() {
            override fun onSuccess(value: T) {
                onNext.invoke(value)
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "DisposableSingleObserver error")
                onError.invoke(e)
            }
        }
    }

    protected fun getDisposableCompletableObserver(
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit = {}
    ): DisposableCompletableObserver {

        return object : DisposableCompletableObserver() {

            override fun onComplete() {
                onComplete.invoke()
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "DisposableCompletableObserver error")
                onError.invoke(e)
            }
        }
    }

    protected fun getDisposableSubscriber(onNext: (T) -> Unit, onError: (Throwable) -> Unit = {}): DisposableSubscriber<T> {

        return object : DisposableSubscriber<T>() {

            override fun onNext(value: T) {
                onNext.invoke(value)
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "DisposableSubscriber error")
                onError.invoke(e)
            }
        }
    }

    fun dispose() {
        disposables.clear()
    }
}