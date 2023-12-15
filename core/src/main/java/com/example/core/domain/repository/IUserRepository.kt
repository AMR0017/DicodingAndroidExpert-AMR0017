package com.example.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.core.data.Resource
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getUser(): Flow<Resource<List<User>>>
    fun searchUser(username: String): Flow<Resource<List<User>>>
    fun getDetailUser(username: String): Flow<Resource<User>>
    fun getFavoriteUser(): Flow<List<User>>
    fun setFavoriteGitUser(user: User, state: Boolean)
    fun checkFav(username: String): LiveData<Boolean>
}