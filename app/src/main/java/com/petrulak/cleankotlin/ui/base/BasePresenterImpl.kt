package com.petrulak.cleankotlin.ui.base

import io.reactivex.disposables.CompositeDisposable


open class BasePresenterImpl : BasePresenterLifecycle {

    val disposables = CompositeDisposable()

    override fun start() {
    }

    override fun stop() {
        disposables.clear()
    }
}
