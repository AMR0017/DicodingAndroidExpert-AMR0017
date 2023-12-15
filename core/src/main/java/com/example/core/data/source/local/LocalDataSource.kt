package com.example.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.core.data.source.local.entity.UserEntity
import com.example.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val favoriteDao:UserDao) {
    fun getAllGitUser() : Flow<List<UserEntity>> = favoriteDao.getAllUser()

    fun setFavorite(userEntity: UserEntity, status:Boolean){
        if (!status){
            favoriteDao.insertFavorite(userEntity)
        }
        else{
            favoriteDao.removeFavorite(userEntity)
        }
    }

    fun isFavorite(username: String): LiveData<Boolean> {
        return favoriteDao.checkFavorite(username)
    }
}