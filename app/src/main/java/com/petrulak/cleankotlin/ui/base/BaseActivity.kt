package com.petrulak.cleankotlin.ui.base;

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.petrulak.cleankotlin.platform.analytics.AnalyticsManager
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import com.petrulak.cleankotlin.platform.navigation.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    @Inject lateinit var eventBus: EventBus
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var analyticsManager: AnalyticsManager

    protected abstract fun inject()
    @LayoutRes protected abstract fun layoutId(): Int
    protected open fun afterLayout(savedInstanceState: Bundle?) {}
    protected open fun onViewsBound() {}

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

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
