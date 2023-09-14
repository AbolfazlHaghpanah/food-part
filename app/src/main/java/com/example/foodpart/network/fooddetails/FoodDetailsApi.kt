package com.example.foodpart.network.fooddetails

import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodDetailsApi {
    @GET("v1/food/{id}")
    suspend fun getFoodDetails(
        @Path("id") id: String
    ): Response<FoodDetailsResponse>

    @POST("v1/food/{id}/report")
    suspend fun reportFood(
        @Path("id") id: String,
        @Body body: ReportBody
    ): Response<ReportResponse>

    @GET("v1/food")
    suspend fun getFoodListByIds(
        @Query("ids") ids : String
    ):Response<FoodListByCategoryResponse>
}