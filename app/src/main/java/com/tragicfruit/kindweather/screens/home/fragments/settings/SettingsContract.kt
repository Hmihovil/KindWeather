package com.tragicfruit.kindweather.screens.home.fragments.settings

import com.tragicfruit.kindweather.screens.WPresenter
import com.tragicfruit.kindweather.screens.WView

interface SettingsContract {

    interface View : WView {
        fun showAlertTimeDialog(initialAlertHour: Int, initialAlertMinute: Int)
        fun updateAlertTimeText(alertHour: Int, alertMinute: Int)
        fun restartAlertService()
        fun updateUnitsText(usesImperial: Boolean)
        fun showChangeUnitsDialog(usesImperial: Boolean)
        fun openWebPage(url: String)
    }

    interface Presenter : WPresenter<View> {
        fun init()
        fun onAlertTimeClicked()
        fun onAlertTimeChanged(hourOfDay: Int, minute: Int)
        fun onUnitsClicked()
        fun onUnitsChanged(imperial: Boolean)
        fun onDarkSkyDisclaimerClicked()
    }

}