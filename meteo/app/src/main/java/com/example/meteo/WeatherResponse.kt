package com.example.meteo.model

data class WeatherResponse(
    val city: City,
    val list: List<HourlyWeather>
)

data class City(
    val name: String
)

data class HourlyWeather(
    val dtTxt: String, // Correspond au champ "dt_txt" dans la réponse JSON
    val main: Main
)

data class Main(
    val temp: Float // Température en degrés Celsius
)
