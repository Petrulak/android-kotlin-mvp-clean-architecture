package com.petrulak.cleankotlin.platform.bus.event

import com.petrulak.cleankotlin.platform.bus.BaseBus
import com.petrulak.cleankotlin.platform.bus.event.events.BaseEvent


open class BaseEventBus<T> : BaseBus<BaseEvent<T>>()