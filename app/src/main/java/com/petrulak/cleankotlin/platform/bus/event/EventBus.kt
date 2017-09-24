package com.petrulak.cleankotlin.platform.bus.event

import com.petrulak.cleankotlin.platform.bus.event.events.FragmentSyncEvent
import com.petrulak.cleankotlin.platform.bus.event.events.WeatherDummyEvent


class EventBus {
    val fragmentSyncEvent = FragmentSyncEvent()
    val weatherDummyEvent = WeatherDummyEvent()
}