package com.example.foodpart.network.category

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryAllResponse(
    val data : List<CategoryDataResponse>
)
