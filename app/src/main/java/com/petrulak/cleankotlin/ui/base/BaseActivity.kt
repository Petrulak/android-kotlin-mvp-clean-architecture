package com.petrulak.cleankotlin.ui.base;

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.petrulak.cleankotlin.platform.analytics.AnalyticsManager
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import com.petrulak.cleankotlin.platform.connectivity.ConnectivityChecker
import com.petrulak.cleankotlin.platform.extensions.lazyFast
import com.petrulak.cleankotlin.platform.navigation.Navigator
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), ConnectivityChecker.Callbacks {

    private val connectivityChecker: ConnectivityChecker by lazyFast { initializeConnectivityChecker() }
    private val disposables = CompositeDisposable()

    @Inject lateinit var eventBus: EventBus
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var analyticsManager: AnalyticsManager

    protected abstract fun inject()
    protected open fun afterLayout(savedInstanceState: Bundle?) {}
    protected open fun onViewsBound() {}
    @LayoutRes protected abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        afterLayout(savedInstanceState)
        onViewsBound()
    }

    @CallSuper
    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        connectivityChecker.start()
    }

    override fun onPause() {
        super.onPause()
        connectivityChecker.stop()
    }

    private fun initializeConnectivityChecker(): ConnectivityChecker {
        return ConnectivityChecker(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager, this)
    }

    override fun onConnected() {
        Timber.i("Internet connection is ON!!")
    }

    override fun onDisconnected() {
        Timber.i("Internet connection is OFF!!")
    }
}
