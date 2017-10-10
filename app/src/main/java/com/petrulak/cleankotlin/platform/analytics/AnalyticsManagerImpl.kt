package com.petrulak.cleankotlin.platform.analytics

import com.petrulak.cleankotlin.BuildConfig


class AnalyticsManagerImpl(private val trackers: List<AnalyticsManager>) : AnalyticsManager {

    override fun trackEvent(event: String) {
        execute { trackers.forEach { it.trackEvent(event) } }
    }

    override fun trackEvent(event: String, map: HashMap<String, Any>) {
        execute { trackers.forEach { it.trackEvent(event, map) } }
    }

    override fun setUser(email: String) {
        execute { trackers.forEach { it.setUser(email) } }
    }

    override fun setCustomAttributes(attributes: HashMap<String, Any>) {
        execute { trackers.forEach { it.setCustomAttributes(attributes) } }
    }

    override fun clear() {
        execute { trackers.forEach { it.clear() } }
    }

    private fun execute(action: () -> Unit) {
        if (!BuildConfig.DEBUG) {
            action.invoke()
        }
    }
}