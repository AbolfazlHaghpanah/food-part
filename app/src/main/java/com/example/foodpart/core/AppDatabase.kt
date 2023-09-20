package com.example.foodpart.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodpart.database.savedfood.SavedFoodDao
import com.example.foodpart.database.savedfood.SavedFoodEntity
import com.example.foodpart.database.user.UserDao
import com.example.foodpart.database.user.UserEntity


@Database(
    entities = [SavedFoodEntity::class,UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun savedFoodsDao():SavedFoodDao

    abstract fun userDao():UserDao

}