package com.example.foodpart.di

import com.example.foodpart.network.category.CategoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class CategoryModule {

    @Provides
    fun provideCategory(retrofit: Retrofit):CategoryApi{
        return retrofit.create(CategoryApi::class.java)
    }

}