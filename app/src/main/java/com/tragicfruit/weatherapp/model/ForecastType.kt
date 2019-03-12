package com.tragicfruit.weatherapp.model

import androidx.annotation.StringRes
import com.tragicfruit.weatherapp.R

enum class ForecastType(@StringRes val label: Int, val units: String) {
    Temp_max(R.string.forecast_type_max_temp, "K"),         // Kelvin
    Temp_min(R.string.forecast_type_min_temp, "K"),         // Kelvin
    Pressure(R.string.forecast_type_pressure, "hPa"),       // hPa
    Humidity(R.string.forecast_type_humidity, "%"),         // %
    Wind_speed(R.string.forecast_type_wind_speed, "m/s"),   // m/s
    Clouds(R.string.forecast_type_clouds, "%"),             // %
    Rain(R.string.forecast_type_rain, "mm/hr"),             // mm/hr
    Snow(R.string.forecast_type_snow, "mm/hr");             // mm/hr

    companion object {
        fun fromString(type: String?) = try {
            ForecastType.valueOf(type!!)
        } catch (e: Exception) {
            null
        }
    }
}