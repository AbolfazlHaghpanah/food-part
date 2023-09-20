package com.example.foodpart.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodpart.network.user.EditUserUsername


@Entity("user-table")
data class UserEntity(
    @PrimaryKey
    val token : String,
    val username: String,
    val id : String?,
    val avatar : String?
)
