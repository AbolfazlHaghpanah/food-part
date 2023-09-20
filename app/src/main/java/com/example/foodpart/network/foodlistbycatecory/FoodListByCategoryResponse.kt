package com.example.foodpart.network.foodlistbycatecory

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FoodListByCategoryResponse(
    val data : List<FoodListByCategoryItem>
)
