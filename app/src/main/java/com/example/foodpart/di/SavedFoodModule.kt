package com.example.foodpart.di

import com.example.foodpart.core.AppDatabase
import com.example.foodpart.database.savedfood.SavedFoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object SavedFoodModule {


    @Provides
    fun provideSavedFoodDatabase(appDatabase: AppDatabase):SavedFoodDao{
        return appDatabase.savedFoodsDao()
    }

}