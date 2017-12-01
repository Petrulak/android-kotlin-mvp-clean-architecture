package com.petrulak.cleankotlin.ui.example2

import com.petrulak.cleankotlin.ui.base.BasePresenter
import com.petrulak.cleankotlin.ui.base.BaseView

interface Example2Contract {
    interface View : BaseView<Presenter> {
        override fun attachPresenter(presenter: Presenter)
        var viewState: ViewState
    }

    interface Presenter : BasePresenter<View> {
        override fun attachView(view: View)
        fun refresh(city: String)
    }
}