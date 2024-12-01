package com.example.meteoApp.data

import com.example.meteoApp.model.BaseResponse
import com.example.meteoApp.model.WeatherForecastModel
import com.example.meteoApp.model.WeatherModel

interface WeatherRepository {
    suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): BaseResponse<WeatherModel>

    suspend fun getWeatherForecastData(
        latitude: Double,
        longitude: Double
    ): BaseResponse<WeatherForecastModel>
}