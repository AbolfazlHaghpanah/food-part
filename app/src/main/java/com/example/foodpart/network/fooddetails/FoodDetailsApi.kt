package com.example.foodpart.network.fooddetails

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodDetailsApi {
    @GET("v1/food/{id}")
    suspend fun getFoodDetails(
        @Path("id") id: String
    ): FoodDetailsResponse

    @POST("v1/food/{id}/report")
    suspend fun reportFood(
        @Path("id") id: String,
        @Body body: ReportBody
    ): Response<ReportResponse>
}