package com.tragicfruit.kindweather.model

import androidx.annotation.StringRes
import com.tragicfruit.kindweather.R
import com.tragicfruit.kindweather.utils.Converter
import com.tragicfruit.kindweather.utils.SharedPrefsHelper

enum class ForecastType(@StringRes val label: Int = -1,
                        minValue: Double = 0.0,
                        maxValue: Double = 100.0,
                        private val metricUnits: String = "",
                        private val imperialUnits: String = "",
                        private val converter: Converter.Measurement = Converter.Default) {

    TEMP_HIGH(R.string.forecast_type_temp_high, -20.0, 60.0, "°C", "°F", Converter.Temperature),
    TEMP_LOW(R.string.forecast_type_temp_low, -90.0, 40.0, "°C", "°F", Converter.Temperature),
    PRECIP_INTENSITY(R.string.forecast_type_precip_intensity, 0.0, 100.0, "mm/h", "in/h", Converter.Precipitation),
    PRECIP_PROBABILITY(R.string.forecast_type_precip_probability, 0.0, 1.0, "%", "%", Converter.Probability),
    HUMIDITY(R.string.forecast_type_humidity, 0.0, 1.0, "%", "%", Converter.Humidity),
    WIND_GUST(R.string.forecast_type_wind_gust, 0.0, 33.0, "km/h", "mph", Converter.WindSpeed),
    UV_INDEX(R.string.forecast_type_uv, 0.0, 10.0),

    UNKNOWN;

    val minValue = minValue
        get() = fromRawValue(field)

    val maxValue = maxValue
        get() = fromRawValue(field)

    val units: String
        get() = getUnits(this)

    fun toRawValue(value: Double): Double {
        return if (SharedPrefsHelper.usesImperialUnits()) {
            converter.fromImperial(value)
        } else {
            converter.fromMetric(value)
        }
    }

    fun fromRawValue(value: Double): Double {
        return if (SharedPrefsHelper.usesImperialUnits()) {
            converter.toImperial(value)
        } else {
            converter.toMetric(value)
        }
    }

    companion object {
        fun fromString(type: String) = try {
            ForecastType.valueOf(type)
        } catch (e: Exception) {
            UNKNOWN
        }

        fun getUnits(type: ForecastType): String {
            return if (SharedPrefsHelper.usesImperialUnits()) {
                type.imperialUnits
            } else {
                type.metricUnits
            }
        }
    }
}