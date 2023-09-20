package com.example.foodpart.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser (user : UserEntity)

    @Query("delete from `user-table`")
    suspend fun deleteUser()

    @Update(UserEntity::class,OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: UserEntity)

    @Query("select * from `user-table`ORDER BY token DESC LIMIT 1")
    fun observeUser(): Flow<UserEntity?>


}