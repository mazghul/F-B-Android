package com.mazghul.influx.Models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    val TabName: String,
    val fnblist: List<Fnblist>
) : Parcelable