package com.petrulak.cleankotlin.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject lateinit var eventBus: EventBus

    open fun initializeDependencies() {}
    @LayoutRes protected abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }
}