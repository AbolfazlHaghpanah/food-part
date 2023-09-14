package com.example.foodpart.network.foodlistbycatecory

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FoodListByCategoryItem(
    val id : String,
    val name : String,
    val image : String?,
    val cookTime : Int?,
    val readyTime : Int?
)
