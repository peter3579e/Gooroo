package com.peter.gooroo.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Process(
    @Json(name = "input") var integer : Int = 0,
    @Json(name = "processed_input")  var processedValue : Double,
    val error: String? = null
): Parcelable {}