package com.example.meteo.feature.detail

import android.os.Parcelable
import com.example.meteo.ui.ItemDailyForecastModelList
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyForecast(
    val weatherDetails: WeatherDetails,
    val dailyForecast: ItemDailyForecastModelList
): Parcelable

@Parcelize
data class WeatherDetails(
    val windSpeed: String,
    val windDegree: String,
    val windGust: String,
    val sunrise: String,
    val sunset: String,
    val humidity: String,
    val pressure: String,
    val cloudiness: String
): Parcelable
