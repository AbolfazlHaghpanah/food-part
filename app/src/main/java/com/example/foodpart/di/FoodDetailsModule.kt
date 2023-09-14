package com.example.foodpart.di

import com.example.foodpart.network.fooddetails.FoodDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)
object FoodDetailsModule {

    @Provides
    fun provideFoodDetails(retrofit: Retrofit):FoodDetailsApi{
        return retrofit.create(FoodDetailsApi::class.java)
    }
}