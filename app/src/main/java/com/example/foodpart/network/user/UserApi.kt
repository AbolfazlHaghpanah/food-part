package com.example.foodpart.network.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("v1/user/register")
    suspend fun registerUser(
        @Body body: RegisterUser
    ): Response<UserResponse>

    @POST("v1/user/login")
    suspend fun loginUser(
        @Body body: RegisterUser
    ): Response<LoginUserData>


    @POST("v1/user/edit")
    suspend fun editUserUsername(
        @Header("Authorization") token : String,
        @Body body: EditUserUsername
    ): Response<EditUserUsernameResponse>


    @POST("v1/user/edit")
    suspend fun editUserPassword(
        @Header("Authorization") token : String,
        @Body body: EditUserPassword
    ): Response<EditUserPasswordResponse>

    @POST("v1/user/edit")
    suspend fun editUserAll(
        @Header("Authorization") token : String,
        @Body body: EditAllUser
    ): Response<EditUserPasswordResponse>

    @GET("v1/user/logout")
    suspend fun logout(
        @Header("Authorization") token : String
    ):Response<Void>


}