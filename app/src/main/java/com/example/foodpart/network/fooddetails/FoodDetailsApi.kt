package com.example.foodpart.network.fooddetails

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodDetailsApi {
    @GET("v1/food/{id}")
    suspend fun getFoodDetails(@Path("id")id : String):FoodDetailsResponse
}