package com.petrulak.cleankotlin.platform.analytics

import com.mixpanel.android.mpmetrics.MixpanelAPI


class MixpanelAnalyticsManager(val mixpanelAPI: MixpanelAPI) : AnalyticsManager {

    override fun trackEvent(event: String) {
        mixpanelAPI.track(event)
    }

    override fun trackEvent(event: String, map: HashMap<String, Any>) {
        mixpanelAPI.trackMap(event, map)
    }

    override fun setUser(email: String) {
        mixpanelAPI.identify(email)
    }

    override fun setCustomAttributes(attributes: HashMap<String, Any>) {
        mixpanelAPI.registerSuperPropertiesMap(attributes)
    }

    override fun clear() {
        mixpanelAPI.reset()
    }
}