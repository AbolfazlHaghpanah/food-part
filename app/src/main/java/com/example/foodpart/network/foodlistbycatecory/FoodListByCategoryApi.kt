package com.example.foodpart.network.foodlistbycatecory

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodListByCategoryApi {

    @GET("v1/food")
    suspend fun getFoodListByCategory(
        @Query("category") categoryId: String
    ): Response<FoodListByCategoryResponse>
}