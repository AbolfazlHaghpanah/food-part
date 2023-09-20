package com.example.foodpart.network.user

import com.example.foodpart.database.user.UserEntity
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LoginUserResponse(
    val token: String,
    val user: UserData
)


@JsonClass(generateAdapter = true)
data class LoginUserData(
    val data: LoginUserResponse
){
    fun toUserEntity():UserEntity{
        return UserEntity(
            token = data.token,
            username = data.user.username,
            id = data.user.id,
            avatar = data.user.avatar
        )
    }
}


