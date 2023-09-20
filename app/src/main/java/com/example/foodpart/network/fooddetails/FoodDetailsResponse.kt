package com.example.foodpart.network.fooddetails

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FoodDetailsResponse(
    val data : FoodDetailsDataResponse,
    val additionalInfo: FoodDetailsAdditionalInfoResponse
)
