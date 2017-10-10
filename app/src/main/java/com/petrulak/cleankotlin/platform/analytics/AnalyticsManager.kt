package com.petrulak.cleankotlin.platform.analytics

interface AnalyticsManager {

    fun setUser(email: String)

    fun setCustomAttributes(attributes: HashMap<String, Any>)

    fun trackEvent(event: String)

    fun trackEvent(event: String, map: HashMap<String, Any>)

    fun clear()
}