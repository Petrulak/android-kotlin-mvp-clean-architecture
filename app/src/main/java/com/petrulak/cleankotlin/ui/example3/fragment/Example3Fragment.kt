package com.petrulak.cleankotlin.ui.example3.fragment

import android.os.Bundle
import android.view.View
import com.petrulak.cleankotlin.App
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.DaggerViewComponent
import com.petrulak.cleankotlin.di.module.PresenterModule
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.ui.base.BaseFragment
import com.petrulak.cleankotlin.ui.example3.fragment.Example3Contract.Presenter
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject


class Example3Fragment : BaseFragment(), Example3Contract.View {

    private var presenter: Presenter? = null

    override fun layoutId() = R.layout.fragment_weather

    override fun initializeDependencies() {
        DaggerViewComponent.builder()
            .applicationComponent(App.instance.appComponent())
            .presenterModule(PresenterModule())
            .build().inject(this)
    }

    @Inject
    override fun attachPresenter(presenter: Presenter) {
        this.presenter = presenter
        this.presenter?.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.start()
        btn_refresh.setOnClickListener { presenter?.refresh() }
    }

    override fun onDestroy() {
        presenter?.stop()
        super.onDestroy()
    }

    override fun showWeather(data: Weather) {
        tv_city.text = data.name
        tv_visibility.text = data.visibility.toString()
    }

    companion object {
        fun newInstance(): Example3Fragment {
            return Example3Fragment()
        }
    }
}
