package com.example.foodpart.network.user

import android.media.session.MediaSession.Token
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserResponse(
    val data : LoginUserResponse
)


@JsonClass(generateAdapter = true)
data class UserData(
    val avatar : String,
    val id : String,
    val username : String
)
