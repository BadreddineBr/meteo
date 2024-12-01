package com.example.meteoApp.data

import com.example.meteoApp.data.remote.RemoteDataSource
import com.example.meteoApp.data.remote.model.toWeatherForecastModel
import com.example.meteoApp.data.remote.model.toWeatherModel
import com.example.meteoApp.model.BaseResponse
import com.example.meteoApp.model.WeatherForecastModel
import com.example.meteoApp.model.WeatherModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {
    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): BaseResponse<WeatherModel> {
        return remoteDataSource.getCurrentWeatherData(latitude, longitude)
            .mapResponse { it.toWeatherModel() }
    }

    override suspend fun getWeatherForecastData(
        latitude: Double,
        longitude: Double
    ): BaseResponse<WeatherForecastModel> {
        return remoteDataSource.getWeatherForecastData(latitude, longitude)
            .mapResponse { it.toWeatherForecastModel() }
    }

    private fun <I : Any, O : Any> BaseResponse<I>.mapResponse(mapper: (I) -> O): BaseResponse<O> {
        return when (this) {
            is BaseResponse.Success -> {
                BaseResponse.Success(mapper(this.data))
            }

            is BaseResponse.Error -> {
                BaseResponse.Error(exception)
            }
        }
    }
}