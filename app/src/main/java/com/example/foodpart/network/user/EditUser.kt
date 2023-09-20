package com.example.foodpart.network.user

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EditAllUser(
    val username: String,
    val oldPassword: String,
    val newpassword: String
)

@JsonClass(generateAdapter = true)
data class EditUserUsername(
    val username: String
)

@JsonClass(generateAdapter = true)
data class EditUserPassword(
    val oldPassword: String,
    val newpassword: String
)


@JsonClass(generateAdapter = true)
data class EditUserUsernameResponse(
    val data: String
)

@JsonClass(generateAdapter = true)
data class EditUserPasswordResponse(
    val data: String,
    val additionalInfo: UserAdditionalInfo
)


@JsonClass(generateAdapter = true)
data class UserAdditionalInfo(
    val token: String
)
