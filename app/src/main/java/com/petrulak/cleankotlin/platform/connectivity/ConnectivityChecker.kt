package com.petrulak.cleankotlin.platform.connectivity

import android.net.ConnectivityManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.strategy.SocketInternetObservingStrategy
import com.petrulak.cleankotlin.platform.extensions.subscribeOnIo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Class which checks if internet connection is present on given device.
 * Since sometimes there is no internet connection present(bad cellular reception, wifi at the airport - sign up needed),
 * class performs pinging of Google servers.
 */
class ConnectivityChecker(
    private val connectivityManager: ConnectivityManager,
    private val callbacks: Callbacks) {

    private val INITIAL_INTERVAL: Long = 1
    private val HW_CHECK_INTERVAL: Long = 500       // how often do you want to check if WIFI/CELLULAR is on ?
    private val PING_CHECK_INTERVAL: Long = 3000    // how often do you want to perform ping ?

    private var isConnected: Boolean? = null
    private var hardwareStateObservable: Observable<Boolean>
    private var connectivityStateObservable: Observable<Boolean>
    private val subscriptions: CompositeDisposable = CompositeDisposable()

    interface Callbacks {
        fun onConnected()
        fun onDisconnected()
    }

    init {
        connectivityStateObservable = initializeConnectivityStateObservable()

        hardwareStateObservable = Observable
            .interval(HW_CHECK_INTERVAL, TimeUnit.MILLISECONDS)
            .timeInterval()
            .flatMap { Observable.just(hasConnection()) }
    }

    private fun initializeConnectivityStateObservable(interval: Long = -1): Observable<Boolean> {
        val observable = when (interval > 0) {
            true  -> Observable.interval(interval, TimeUnit.MILLISECONDS)
            false -> Observable.just(INITIAL_INTERVAL)
        }
        return observable.flatMap {
            ReactiveNetwork.observeInternetConnectivity(SocketInternetObservingStrategy())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun start() {
        startConnectivityStateChecker()
    }

    private fun startHardwareStateChecker() {
        subscriptions.add(subscribeOnIo(hardwareStateObservable, ({ handleConnectionChange(it) })))
    }

    private fun startConnectivityStateChecker() {
        subscriptions.add(subscribeOnIo(connectivityStateObservable, ({ handleConnectionChange(it) })))
    }

    private fun handleConnectionChange(value: Boolean) {
        if (isConnected == null || value != isConnected) {
            executeCallbacks(value)
            processConnectionChange(value)
        }
        isConnected = value
    }

    private fun processConnectionChange(isConnected: Boolean) {
        when (isConnected) {
            true  -> scheduleWhenConnectivityIsAvailable()
            false -> scheduleWhenNoConnectivity()
        }
    }

    private fun scheduleWhenNoConnectivity() {
        subscriptions.clear()
        connectivityStateObservable = initializeConnectivityStateObservable(HW_CHECK_INTERVAL)
        startConnectivityStateChecker()
    }

    private fun scheduleWhenConnectivityIsAvailable() {
        subscriptions.clear()
        startHardwareStateChecker()
        connectivityStateObservable = initializeConnectivityStateObservable(PING_CHECK_INTERVAL)
        startConnectivityStateChecker()

    }

    private fun executeCallbacks(isConnected: Boolean) {

        when (isConnected) {
            true  -> callbacks.onConnected()
            false -> callbacks.onDisconnected()
        }
    }

    private fun hasConnection(): Boolean {
        return (connectivityManager.activeNetworkInfo != null
            && connectivityManager.activeNetworkInfo.isAvailable
            && connectivityManager.activeNetworkInfo.isConnected)
    }

    fun stop() {
        subscriptions.clear()
    }
}