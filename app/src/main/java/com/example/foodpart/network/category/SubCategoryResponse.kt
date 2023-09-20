package com.example.foodpart.network.category

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SubCategoryResponse(
    val id : String,
    val name: String,
)
