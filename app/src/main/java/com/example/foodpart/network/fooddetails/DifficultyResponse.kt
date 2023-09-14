package com.example.foodpart.network.fooddetails

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DifficultyResponse(
    val id: String,
    val name: String
)
