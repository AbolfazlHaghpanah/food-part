package com.example.foodpart.di

import com.example.foodpart.network.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUser(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}