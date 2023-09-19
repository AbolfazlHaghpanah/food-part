package com.example.foodpart.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodpart.database.savedfood.SavedFoodDao
import com.example.foodpart.database.savedfood.SavedFoodEntity


@Database(
    entities = [SavedFoodEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedFoodsDao():SavedFoodDao

}