package com.petrulak.cleankotlin.platform.logging

import android.util.Log
import timber.log.Timber

class ErrorReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        // Crashlytics.log(priority, tag, message)

        if (t != null) {
            //  Crashlytics.logException(t)
        }
    }
}