package com.petrulak.cleankotlin.platform.bus.event.events

import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.platform.bus.event.BaseEventBus

class WeatherDummyEvent : BaseEventBus<Weather>() {

    companion object {
        val ACTION_HELLO = "ACTION_HELLO"
    }
}