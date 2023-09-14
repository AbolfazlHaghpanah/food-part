package com.example.foodpart.network.fooddetails

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ReportBody(
    val description : String
)


@JsonClass(generateAdapter = true)
data class ReportResponse(
    val data : String
)
