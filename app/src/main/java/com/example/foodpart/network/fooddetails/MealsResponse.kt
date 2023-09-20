package com.example.foodpart.network.fooddetails

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MealsResponse(
    val id :String,
    val name :String
)
