package com.petrulak.cleankotlin.ui.example2

import com.petrulak.cleankotlin.App
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.DaggerViewComponent
import com.petrulak.cleankotlin.di.module.PresenterModule
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

/**
 * Example 2 shows how to inject your presenters within Activity level and how to
 * get weather data from remote source (REST API).
 */
class Example2Activity : BaseActivity(), Example2Contract.View {

    private var presenter: Example2Contract.Presenter? = null

    override fun layoutId() = R.layout.fragment_weather

    override fun onViewsBound() {
        presenter?.start()
        btn_refresh.setOnClickListener { presenter?.refresh() }
    }

    override fun inject() {
        DaggerViewComponent.builder()
            .presenterModule(PresenterModule())
            .applicationComponent(App.instance.appComponent())
            .build().inject(this)
    }

    @Inject
    override fun attachPresenter(presenter: Example2Contract.Presenter) {
        this.presenter = presenter
        this.presenter?.attachView(this)
    }

    override fun showWeather(data: Weather) {
        tv_city.text = data.name
        tv_visibility.text = data.visibility.toString()
    }

    override fun onDestroy() {
        presenter?.stop()
        super.onDestroy()
    }

}
