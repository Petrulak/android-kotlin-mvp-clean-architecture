package com.petrulak.cleankotlin.platform.bus.event.events

import com.petrulak.cleankotlin.platform.bus.event.BaseEventBus

class FragmentSyncEvent : BaseEventBus<Any>() {

    companion object {
        val ACTION_SYNC_ON = "SYNC_ON"
        val ACTION_SYNC_OFF = "SYNC_OFF"
    }
}