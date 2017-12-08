package com.petrulak.cleankotlin.ui.example1

import android.os.Bundle
import com.petrulak.cleankotlin.App
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.DaggerViewComponent
import com.petrulak.cleankotlin.ui.base.BaseActivity
import com.petrulak.cleankotlin.ui.example1.fragment.Example1Fragment
import timber.log.Timber

/**
 * Example 1 shows how to inject your presenters within Fragment level and how to get data from
 * local and remote
 */
class Example1Activity : BaseActivity() {

    override fun layoutId() = R.layout.activity_weather

    override fun afterLayout(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bindFragment()
        }
        extractParcelable()
    }

    private fun extractParcelable() {
        intent.extras.getParcelable<ExampleParcelable>("parcelable")?.let {
            Timber.i("Parcelable  message = ${it.message}")
        }
    }

    private fun bindFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, Example1Fragment.newInstance())
            .commit()
    }

    override fun inject() {
        DaggerViewComponent.builder()
            .applicationComponent(App.instance.appComponent())
            .build().inject(this)
    }
}
