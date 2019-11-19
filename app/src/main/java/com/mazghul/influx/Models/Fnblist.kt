package com.mazghul.influx.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fnblist(
    val Cinemaid: String,
    val ImageUrl: String,
    val ImgUrl: String,
    val ItemPrice: String,
    val Name: String,
    val OtherExperience: String,
    val PriceInCents: String,
    val SevenStarExperience: String,
    val SubItemCount: Int,
    val TabName: String,
    val VegClass: String,
    val VistaFoodItemId: String,
    val subitems: List<FoodSubItems>
) : Parcelable