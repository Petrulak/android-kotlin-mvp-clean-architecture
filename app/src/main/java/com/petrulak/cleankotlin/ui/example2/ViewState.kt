package com.petrulak.cleankotlin.ui.example2

import com.petrulak.cleankotlin.domain.model.Weather


sealed class ViewState {
    class Init : ViewState()
    class Loading : ViewState()
    class Success(val item: Weather) : ViewState()
    class LoadingFinished : ViewState()
    class Error(val error: Throwable) : ViewState()
}