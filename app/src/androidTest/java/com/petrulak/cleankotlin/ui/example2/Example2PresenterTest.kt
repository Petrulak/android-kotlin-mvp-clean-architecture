package com.petrulak.cleankotlin.ui.example2

import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class Example2PresenterTest {

    val TAG = "Example2PresenterTest"
    val mockUseCase = mock(GetWeatherRemotelyUseCase::class.java)
    val mockView = mock(Example2Contract.View::class.java)
    val presenter = Example2Presenter(mockUseCase)

    val CITY = "Bratislava"

    @Before
    fun setUp() {
        presenter.attachView(mockView)
    }

    @Test
    fun shouldDisplayError() {
        val errorMsg = "Errorrrr"
        val error = IllegalArgumentException(errorMsg)
        Mockito.`when`(mockUseCase.execute(CITY)).thenReturn(Single.error(error))
        presenter.refresh(CITY)

/*  TODO make this happen
        Log.e(TAG, "viewState = " + presenter.view?.viewState.toString());
        Log.e(TAG, "mockvie = " + mockView.viewState.toString());

        verify(mockView).viewState = Matchers.isA(ViewState.Loading::class.java)
        verify(mockView).viewState = isA<ViewState.Error>()
        verifyNoMoreInteractions(mockView)
        */

    }

}