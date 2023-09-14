package com.example.foodpart.network.fooddetails

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FoodDetailsDataResponse(

    val id : String,
    val categoryId : String,
    val count : String?,
    val name : String,
    val image: String,
    val difficulty : String,
    val point : String?,
    val cookTime : Int?,
    val readyTime :Int?,
    val recipe : String,
    val ingredients : String
)
