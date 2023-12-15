package com.example.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.core.data.Resource
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getUser() : Flow<Resource<List<User>>>
    fun searchUser(username: String): Flow<Resource<List<User>>>
    fun getDetailUser(username:String) : Flow<Resource<User>>
    fun getFavoriteUser() : Flow<List<User>>
    fun setFavoriteUser(user: User, state:Boolean)
    fun checkFav(username: String) : LiveData<Boolean>
}