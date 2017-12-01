package com.petrulak.cleankotlin.ui.example2

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.petrulak.cleankotlin.MockApp
import com.petrulak.cleankotlin.R
import com.petrulak.cleankotlin.di.component.ApplicationMockComponent
import com.petrulak.cleankotlin.di.component.DaggerViewMockComponent
import com.petrulak.cleankotlin.di.module.PresenterMockModule
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class Example2ActivityTest {

    @get:Rule
    var activity = ActivityTestRule(Example2Activity::class.java, true, false)   // do not launch the activity immediately

    @Inject lateinit var useCase: GetWeatherRemotelyUseCase

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as MockApp
        val component = app.applicationComponent as ApplicationMockComponent

        DaggerViewMockComponent.builder()
            .presenterMockModule(PresenterMockModule())
            .applicationMockComponent(component)
            .build().inject(this)
    }

    @Test
    fun shouldUpdateUIAfterObtainingWeatherData() {
        val mockedWeather = Weather(1L, "Bratislava", 456)
        val mockedSingle = Single.just(mockedWeather)
        Mockito.`when`(useCase.execute(mockedWeather.name)).thenReturn(mockedSingle)

        activity.launchActivity(Intent())
        onView(withId(R.id.tv_city)).check(matches(withText(mockedWeather.name)))
    }
}