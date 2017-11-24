package com.petrulak.cleankotlin

import android.app.Application
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.petrulak.cleankotlin.di.component.ApplicationComponent
import com.petrulak.cleankotlin.di.component.DaggerApplicationComponent
import com.petrulak.cleankotlin.di.module.*
import com.petrulak.cleankotlin.platform.logging.ErrorReportingTree
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

open class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = initializeAppComponent()
        initialize()
    }

    open fun initializeAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataModule(DataModule())
            .networkModule(NetworkModule())
            .mapperModule(MapperModule())
            .interactorModule(InteractorModule())
            .build()
    }

    private fun initialize() {
        if (BuildConfig.DEBUG) {

            Timber.plant(Timber.DebugTree())

            // https://developer.android.com/reference/android/os/StrictMode.html
            StrictMode.enableDefaults()

            // http://facebook.github.io/stetho/
            Stetho.initializeWithDefaults(this)

            // https://github.com/square/leakcanary
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)

        } else {
            Timber.plant(ErrorReportingTree())
            // https://medium.com/fuzz/getting-the-most-out-of-crashlytics-380afb703876
            // Crashlytics.setString("git_sha", BuildConfig.GIT_SHA)
        }
    }

    fun appComponent(): ApplicationComponent {
        return applicationComponent
    }

    companion object {
        lateinit var instance: App
    }
}