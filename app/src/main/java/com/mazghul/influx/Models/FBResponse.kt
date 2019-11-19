package com.mazghul.influx.Models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FBResponse(
    val Currency: String,
    val FoodList: List<Food>,
    val status: Status
) : Parcelable