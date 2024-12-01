package com.example.meteoApp.data.local

import android.location.Location

interface LocationPreferenceManager {

    suspend fun getLocation(): Location?
    suspend fun saveLocation(latitude: Double, longitude: Double)
}