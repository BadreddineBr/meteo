package com.example.meteoApp.data.remote

import com.example.meteoApp.data.remote.model.CurrentWeatherDto
import com.example.meteoApp.data.remote.model.WeatherForecastDto
import com.example.meteoApp.model.BaseResponse

interface RemoteDataSource {
    suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): BaseResponse<CurrentWeatherDto>

    suspend fun getWeatherForecastData(
        latitude: Double,
        longitude: Double
    ): BaseResponse<WeatherForecastDto>
}