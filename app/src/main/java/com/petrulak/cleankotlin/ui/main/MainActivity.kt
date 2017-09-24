package com.petrulak.cleankotlin.ui.main

import com.petrulak.cleankotlin.App
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.DaggerViewComponent
import com.petrulak.cleankotlin.ui.base.BaseActivity
import com.petrulak.cleankotlin.ui.example1.Example1Activity
import com.petrulak.cleankotlin.ui.example2.Example2Activity
import com.petrulak.cleankotlin.ui.example3.Example3Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_main

    override fun onViewsBound() {
        logAnalytics()
        btn_one.setOnClickListener { button1Clicked() }
        btn_two.setOnClickListener { button2Clicked() }
        btn_three.setOnClickListener { button3Clicked() }
    }

    override fun inject() {
        DaggerViewComponent.builder()
            .applicationComponent(App.instance.appComponent())
            .build().inject(this)
    }

    private fun button1Clicked() {
        navigator.navigate(this, Example1Activity::class.java)
    }

    private fun button2Clicked() {
        navigator.navigate(this, Example2Activity::class.java)
    }

    private fun button3Clicked() {
        navigator.navigate(this, Example3Activity::class.java)
    }

    private fun logAnalytics() {

        analyticsManager.setUser("john@doe.com")

        analyticsManager.trackEvent("Activity 1")

        val map = hashMapOf("userRole" to "admin", "userAge" to 30, "isPremiumUser" to true)
        analyticsManager.trackEvent("Activity 2", map)
    }
}