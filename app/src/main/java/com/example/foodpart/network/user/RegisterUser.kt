package com.example.foodpart.network.user

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RegisterUser(
    val username :String,
    val password :String
)
