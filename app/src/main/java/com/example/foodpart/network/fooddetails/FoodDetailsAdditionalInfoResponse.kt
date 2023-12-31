package com.example.foodpart.network.fooddetails

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FoodDetailsAdditionalInfoResponse(
    val difficulty : DifficultyResponse?,
    val meals : List<MealsResponse>?,
    val similarFoods : List<String>?
)
