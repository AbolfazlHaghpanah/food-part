package com.example.foodpart.network.foodlistbycatecory

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodListByCategoryApi {

    @GET("v1/food")
    suspend fun getFoodListByCategory(
        @Query("category") categoryId: String
    ): Response<FoodListByCategoryResponse>


    @GET("v1/food")
    suspend fun getFoodListByMeals(
        @Query("meal") mealId: String
    ): Response<FoodListByCategoryResponse>

    @GET("v1/search")
    suspend fun getSearchedFoodList(
        @Query("search") search: String
    ): Response<FoodListByCategoryResponse>

    @GET("v1/what-to-cook")
    suspend fun getWhatToCookFoodList(
        @Query("ingredients") ingredients :String,
        @Query("ingredients") difficulty :Int?,
        @Query("ingredients") timeLimit :Int,

    ):Response<FoodListByCategoryResponse>

    @GET("v1/food")
    suspend fun getFoodListByIds(
        @Query("ids") ids : String
    ):Response<FoodListByCategoryResponse>


}