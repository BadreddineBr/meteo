package com.example.meteoApp.model

data class WeatherModel(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val sun: Sun,
    val cityName: String,
    val date: Long
)

data class Sun(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)