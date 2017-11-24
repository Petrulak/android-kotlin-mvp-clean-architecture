package com.petrulak.cleankotlin.ui.example3.fragment

import android.util.Log
import com.petrulak.cleankotlin.di.scope.ViewScope
import com.petrulak.cleankotlin.domain.interactor.definition.GetWeatherRemotelyUseCase
import com.petrulak.cleankotlin.domain.model.Weather
import com.petrulak.cleankotlin.platform.bus.data.DataBus
import com.petrulak.cleankotlin.platform.bus.event.EventBus
import com.petrulak.cleankotlin.platform.bus.event.events.BaseEvent
import com.petrulak.cleankotlin.platform.bus.event.events.FragmentSyncEvent
import com.petrulak.cleankotlin.platform.extensions.getDisposableSingleObserver
import com.petrulak.cleankotlin.ui.base.BasePresenterImpl
import com.petrulak.cleankotlin.ui.example3.fragment.Example3Contract.View
import dagger.internal.Preconditions.checkNotNull
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

@ViewScope
class Example3Presenter
@Inject
constructor(private val getWeatherRemotelyUseCase: GetWeatherRemotelyUseCase,
            private val dataBus: DataBus,
            private val eventBus: EventBus) : BasePresenterImpl(), Example3Contract.Presenter {

    private var view: View? = null

    private val dataDisposable = CompositeDisposable()

    override fun attachView(view: View) {
        this.view = checkNotNull(view)
    }

    override fun start() {
        super.start()
        refresh()
        subscribeToData()
        subscribeToFragmentSyncEvents()
        //  subscribeToDummyEvents()
    }

    private fun subscribeToData() {
        val disposable = dataBus.weatherDataBus.flowable.subscribe({ view?.showWeather(it) })
        dataDisposable.add(disposable)
    }

    private fun subscribeToFragmentSyncEvents() {
        val disposable = eventBus.fragmentSyncEvent.flowable.subscribe({ processEvent(it) })
        disposables.add(disposable)
    }

    /**
     * You can consume data/payload  which is included in the event.
     */
    private fun subscribeToDummyEvents() {
        val disposable = eventBus.weatherDummyEvent.flowable.subscribe({ processDummyEvent(it) })
        disposables.add(disposable)
    }

    private fun processDummyEvent(event: BaseEvent<Weather>) {
        //payload can be null, we have to perform null check
        event.payload?.let {
            val payload = it
            Log.e("Hello", "This is payload: $payload")
        }
    }

    private fun processEvent(event: BaseEvent<Any>) {
        when (event.eventType) {
            FragmentSyncEvent.ACTION_SYNC_OFF -> dataDisposable.clear()
            FragmentSyncEvent.ACTION_SYNC_ON  -> subscribeToData()
        }
    }

    override fun stop() {
        super.stop()
        dataDisposable.clear()
    }

    override fun refresh() {
        val disposable = getWeatherRemotelyUseCase
            .execute("London,uk")
            .subscribeWith(getDisposableSingleObserver({ onSuccess(it) }, { onError(it) }))
        disposables.add(disposable)
    }

    private fun onSuccess(weather: Weather) {
        //Changing visibility value manually to see changes in the UI
        val modified = Weather(weather.id, weather.name, Random().nextInt(80 - 65) + 65)
        dataBus.weatherDataBus.emmit(modified)
        view?.showWeather(modified)
    }

    private fun onError(t: Throwable) {

    }

}
