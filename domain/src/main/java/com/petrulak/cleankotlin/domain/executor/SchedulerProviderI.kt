package com.petrulak.cleankotlin.domain.executor


import io.reactivex.Scheduler

interface SchedulerProviderI {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}