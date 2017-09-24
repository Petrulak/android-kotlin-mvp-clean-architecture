package com.petrulak.cleankotlin.platform.bus

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


open class BaseBus<T> {

    protected val bus = PublishRelay.create<T>()

    val flowable: Flowable<T> get() = bus.toFlowable(BackpressureStrategy.LATEST)

    fun emmit(event: T) = bus.accept(event)
}