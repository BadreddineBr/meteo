package com.example.meteoApp.util

import android.Manifest

object Location {
    val requiredPermission = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ).toTypedArray()
}