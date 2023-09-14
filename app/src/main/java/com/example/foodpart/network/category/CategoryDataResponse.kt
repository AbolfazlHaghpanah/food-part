package com.example.foodpart.network.category

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CategoryDataResponse(
    val id : String,
    val name : String,
    val image : String?,
    val subCategories : List<SubCategoryResponse>?
)
