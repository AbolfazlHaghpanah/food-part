package com.example.foodpart.database.savedfood

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryItem


@Entity("saved-foods")
data class SavedFoodEntity(
    @PrimaryKey
    val id : String,
    val name : String,
    val image : String?,
    val cookTime : Int?,
    val readyTime : Int?
){
    fun toFoodListByCategoryItem(): FoodListByCategoryItem{
        return FoodListByCategoryItem(
            id, name, image, cookTime, readyTime
        )
    }
}

