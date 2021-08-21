package com.peter.gooroo.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Combine(
        val error: String? = null,
        @Json(name = "input") var inputTen: List<Double>,
        @Json(name = "combined_result") var combine_result: String
) : Parcelable {}

@Parcelize
data class PostTen(
        @Json(name = "input") var input: List<Double>
) : Parcelable {}
