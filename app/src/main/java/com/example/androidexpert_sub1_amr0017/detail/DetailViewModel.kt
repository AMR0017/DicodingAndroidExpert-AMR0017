package com.example.androidexpert_sub1_amr0017.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.User
import com.example.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getDetailUser(username:String): LiveData<Resource<User>> {
        return userUseCase.getDetailUser(username).asLiveData()
    }
    fun checkFav(username:String) = userUseCase.checkFav(username)
    fun setFav(user:User, newStatus:Boolean) = userUseCase.setFavoriteUser(user, newStatus)
}