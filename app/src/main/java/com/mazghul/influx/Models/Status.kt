package com.mazghul.influx.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Status(
    val Description: String,
    val Id: String
) : Parcelable