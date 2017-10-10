package com.petrulak.cleankotlin.ui.example3

import android.os.Bundle
import com.petrulak.cleankotlin.App
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.DaggerViewComponent
import com.petrulak.cleankotlin.platform.bus.event.events.BaseEvent
import com.petrulak.cleankotlin.platform.bus.event.events.FragmentSyncEvent
import com.petrulak.cleankotlin.ui.base.BaseActivity
import com.petrulak.cleankotlin.ui.example3.fragment.Example3Fragment
import kotlinx.android.synthetic.main.activity_split_view.*

/**
 * Example 3 shows how to inject your presenters within Fragment level and how to
 * synchronize data between 2 fragments.
 */
class Example3Activity : BaseActivity() {

    override fun layoutId() = R.layout.activity_split_view

    override fun afterLayout(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bindFragment()
        }
    }

    private fun bindFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame1, Example3Fragment.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame2, Example3Fragment.newInstance())
            .commit()
    }

    override fun onViewsBound() {
        switchBtn.setOnCheckedChangeListener({ _, isChecked ->
            when (isChecked) {
                true  -> eventBus.fragmentSyncEvent.emmit(BaseEvent(FragmentSyncEvent.ACTION_SYNC_ON))
                false -> eventBus.fragmentSyncEvent.emmit(BaseEvent(FragmentSyncEvent.ACTION_SYNC_OFF))
            }
        })

        /**
         * This is how you can add some data/payload to event.
         * You can see [com.petrulak.cleankotlin.ui.example3.fragment.Example3Presenter.processDummyEvent] how to process event.
         * val dummy = Weather(1, "", 1)
         * eventBus.weatherDummyEvent.emmit(BaseEvent(WeatherDummyEvent.ACTION_HELLO, dummy))
         */
    }

    override fun inject() {
        DaggerViewComponent.builder()
            .applicationComponent(App.instance.appComponent())
            .build().inject(this)
    }
}
