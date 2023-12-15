package com.example.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(user: UserEntity)

    @Delete
    fun removeFavorite(user: UserEntity)

    @Update
    fun updateFavorite(user: UserEntity)

    @Query("SELECT * FROM favorite ORDER BY login ASC")
    fun getAllUser(): Flow<List<UserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE login =  :username)")
    fun checkFavorite(username: String): LiveData<Boolean>
}