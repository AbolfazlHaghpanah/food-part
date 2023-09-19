package com.example.foodpart.database.savedfood

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("saved-foods")
data class SavedFoodEntity(
    @PrimaryKey
    val id : String,
    val name : String,
    val image : String?,
    val cookTime : Int?,
    val readyTime : Int?
)

