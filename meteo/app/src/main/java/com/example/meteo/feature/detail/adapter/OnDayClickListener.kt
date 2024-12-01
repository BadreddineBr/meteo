package com.example.meteo.feature.detail

import com.example.meteo.ui.ItemDailyForecastModel


interface OnDayClickListener {
    fun onDayClick(itemDailyForecastModel: ItemDailyForecastModel)
}