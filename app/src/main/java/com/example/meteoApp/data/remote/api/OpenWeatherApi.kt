package com.example.meteoApp.data.remote.api

import com.example.meteoApp.BuildConfig
import com.example.meteoApp.data.remote.model.CurrentWeatherDto
import com.example.meteoApp.data.remote.model.WeatherForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<CurrentWeatherDto>

    @GET("/data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<WeatherForecastDto>
}