package com.example.foodpart.di

import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object FoodListByCategoryModule {

    @Provides
    fun provideFoodListByCategory(retrofit: Retrofit):FoodListByCategoryApi{
        return retrofit.create(FoodListByCategoryApi::class.java)
    }
}