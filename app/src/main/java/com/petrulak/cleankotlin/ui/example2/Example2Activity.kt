package com.petrulak.cleankotlin.ui.example2

import android.view.View
import com.petrulak.cleankotlin.App
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.DaggerViewComponent
import com.petrulak.cleankotlin.di.module.PresenterModule
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Example 2 shows how to inject your presenters within Activity level and how to
 * get weather data from remote source (REST API).
 */
class Example2Activity : BaseActivity(), Example2Contract.View {

    private var presenter: Example2Contract.Presenter? = null

    override fun layoutId() = R.layout.fragment_weather

    override fun onViewsBound() {
        presenter?.start()
        btn_refresh.setOnClickListener { presenter?.refresh(Example2Presenter.CITY) }
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

    override var viewState: ViewState by Delegates.observable<ViewState>(ViewState.Init(), { _, old, new ->
        processStateChange(new)
    })

    private fun processStateChange(new: ViewState) {
        //using `when` as a statement, forces us to implement all possible values of `ViewState`
        return when (new) {
            is ViewState.Init            -> initialize()
            is ViewState.Loading         -> loading()
            is ViewState.Success         -> onNewItem(new.item)
            is ViewState.Error           -> onError(new.error)
            is ViewState.LoadingFinished -> finishLoading()
        }
    }

    private fun initialize() {
        pb_refresh.visibility = View.GONE
    }

    private fun loading() {
        pb_refresh.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
    }

    private fun onNewItem(data: Weather) {
        tv_city.text = data.name
        tv_visibility.text = data.visibility.toString()
    }

    private fun finishLoading() {
        pb_refresh.visibility = View.GONE
    }

    private fun onError(e: Throwable) {
        tv_error.visibility = View.VISIBLE
        tv_error.text = e.message
    }

    override fun onDestroy() {
        presenter?.stop()
        super.onDestroy()
    }

}
