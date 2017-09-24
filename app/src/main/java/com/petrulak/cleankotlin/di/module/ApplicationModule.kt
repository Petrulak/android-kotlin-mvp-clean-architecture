package com.petrulak.cleankotlin.di.module

import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.domain.executor.SchedulerProvider
import com.petrulak.cleankotlin.domain.executor.SchedulerProviderI
import com.petrulak.cleankotlin.platform.analytics.AnalyticsManager
import com.petrulak.cleankotlin.platform.analytics.MixpanelAnalyticsActions
import com.petrulak.cleankotlin.platform.bus.data.DataBus
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import com.petrulak.cleankotlin.platform.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun context(): Context {
        return context
    }

    @Singleton
    @Provides
    internal fun eventBus(): EventBus {
        return EventBus()
    }

    @Singleton
    @Provides
    internal fun dataBus(): DataBus {
        return DataBus()
    }

    @Singleton
    @Provides
    internal fun schedulerProvider(): SchedulerProviderI {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    internal fun navigator(): Navigator {
        return Navigator()
    }

    @Singleton
    @Provides
    internal fun provideAnalyticsManager(context: Context): AnalyticsManager {
        val mixPanel = MixpanelAnalyticsActions(MixpanelAPI.getInstance(context, context.getString(R.string.mixpanel_key)))
        return AnalyticsManager(listOf(mixPanel))
    }

}
