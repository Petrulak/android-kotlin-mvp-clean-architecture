package com.petrulak.cleankotlin.platform.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber


fun <T1> subscribeOnIo(observable: Flowable<T1>, onNext: (T1) -> Unit = {}, onError: (Throwable) -> Unit = {}): Disposable {
    return observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSubscriber<T1>() {

            override fun onNext(t: T1) {
                onNext.invoke(t)
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
                onError.invoke(e)
            }
        })
}

fun <T1> subscribeOnIo(observable: Observable<T1>, onNext: (T1) -> Unit = {}, onError: (Throwable) -> Unit = {}): Disposable {
    return observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<T1>() {

            override fun onNext(t: T1) {
                onNext.invoke(t)
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                onError.invoke(e)
            }
        })
}

fun <T1> subscribeOnIo(single: Single<T1>, onSuccess: (T1) -> Unit = {}, onError: (Throwable) -> Unit = {}): Disposable {
    return single
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSingleObserver<T1>() {

            override fun onSuccess(t: T1) {
                onSuccess.invoke(t)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
                onError.invoke(e)
            }
        })
}

fun subscribeOnIo(completable: Completable, onCompleted: () -> Unit, onError: (Throwable) -> Unit) {
    return completable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableCompletableObserver() {

            override fun onComplete() {
                onCompleted.invoke()
            }

            override fun onError(e: Throwable) {
                onError.invoke(e)
            }
        })
}

/**
 * Since we don't need our objects to be thread safe most of the time, we can use  `LazyThreadSafetyMode.NONE` which has lower
 * overhead.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}