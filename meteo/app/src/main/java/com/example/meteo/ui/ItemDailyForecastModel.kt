package com.example.meteo.ui

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class ItemDailyForecastModelList(
    val itemDailyForecastModel: List<ItemDailyForecastModel>
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ItemDailyForecastModel.CREATOR)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(itemDailyForecastModel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemDailyForecastModelList> {
        override fun createFromParcel(parcel: Parcel): ItemDailyForecastModelList {
            return ItemDailyForecastModelList(parcel)
        }

        override fun newArray(size: Int): Array<ItemDailyForecastModelList?> {
            return arrayOfNulls(size)
        }
    }
}

@Parcelize
data class ItemDailyForecastModel(
    val id: Long = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE,
    val mainDescription: String,
    val description: String,
    val weatherDate: WeatherDate,
    val minTemp: String,
    val maxTemp: String
): Parcelable
