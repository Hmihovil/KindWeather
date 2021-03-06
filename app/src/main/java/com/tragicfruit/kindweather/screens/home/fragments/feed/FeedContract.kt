package com.tragicfruit.kindweather.screens.home.fragments.feed

import com.tragicfruit.kindweather.model.ForecastPeriod
import com.tragicfruit.kindweather.model.WeatherNotification
import com.tragicfruit.kindweather.screens.WPresenter
import com.tragicfruit.kindweather.screens.WView
import io.realm.RealmResults

interface FeedContract {

    interface View : WView {
        fun refreshFeed()
        fun initView(feedData: RealmResults<WeatherNotification>)
        fun showForecastScreen(forecast: ForecastPeriod, timeCreatedMillis: Long, color: Int)
        fun showEmptyState(show: Boolean)
        fun showConditionsScreen()
    }

    interface Presenter : WPresenter<View> {
        fun init()
        fun onFeedItemClicked(notification: WeatherNotification)
        fun onSetupConditionsClicked()
    }

}