package com.example.foodpart.network.user

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserResponse(
    val data : UserData
)


@JsonClass(generateAdapter = true)
data class UserData(
    val avatar : String,
    val id : String,
    val username : String
)
