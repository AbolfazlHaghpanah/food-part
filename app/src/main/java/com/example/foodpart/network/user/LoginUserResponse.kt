package com.example.foodpart.network.user

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LoginUserResponse(
    val token: String,
    val user: UserResponse
)
