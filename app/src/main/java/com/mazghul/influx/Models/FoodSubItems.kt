package com.mazghul.influx.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodSubItems(
    val Name: String,
    val PriceInCents: String,
    val SubitemPrice: String,
    val VistaParentFoodItemId: String,
    val VistaSubFoodItemId: String
) : Parcelable