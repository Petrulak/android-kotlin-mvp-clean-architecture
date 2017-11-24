package com.petrulak.cleankotlin.ui.example2

import android.support.test.runner.AndroidJUnit4
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class Example2PresenterTest {

    lateinit var presenter: Example2Presenter

    val CITY = "Bratislava"

    @Before
    fun setUp() {

        val mockUseCase = mock(GetWeatherRemotelyUseCase::class.java)
        val mockView = mock(Example2Contract.View::class.java)

        val mockedWeather = Weather(1L, CITY, 456)
        val mockedSingle = Single.just(mockedWeather)
        Mockito.`when`(mockUseCase.execute(CITY)).thenReturn(mockedSingle)

        presenter = Example2Presenter(mockUseCase)
        presenter.attachView(mockView)
    }

    @Test
    fun refresh() {
        //TODO RXJava test
    }

}