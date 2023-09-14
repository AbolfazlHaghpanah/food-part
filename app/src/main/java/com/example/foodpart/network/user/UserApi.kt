package com.example.foodpart.network.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("v1/user/register")
    suspend fun RegisterUser(
        @Body body: RegisterUser
    ): Response<UserResponse>

    @POST("v1/user/login")
    suspend fun loginUser(
        @Body body: RegisterUser
    ): Response<LoginUserResponse>


    @POST("v1/user/login")
    suspend fun editUserUsername(
        @Body body: EditUserUsername
    ): Response<EditUserUsernameResponse>


    @POST("v1/user/login")
    suspend fun editUserPassword(
        @Body body: EditUserPassword
    ): Response<EditUserPasswordResponse>

    @POST("v1/user/login")
    suspend fun editUserAll(
        @Body body: EditAllUser
    ): Response<EditUserPasswordResponse>
}