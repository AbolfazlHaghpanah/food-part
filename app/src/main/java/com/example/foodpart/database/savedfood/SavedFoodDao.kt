package com.example.foodpart.database.savedfood

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface SavedFoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food : SavedFoodEntity)

    @Query("Select * from `saved-foods`")
    fun observeFoods():Flow<List<SavedFoodEntity>>




}