package com.example.meteo.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemThreeHourForecastModel(
    val temp: String,
    val weatherDate: WeatherDate,
    val mainDescription: String,
    val description: String
): Parcelable

@Parcelize
data class WeatherDate(
    val hour: String,
    val day: String,
    val isDayNight: Boolean
): Parcelable
