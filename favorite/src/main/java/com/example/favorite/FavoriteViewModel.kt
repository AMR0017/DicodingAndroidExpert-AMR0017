package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.UserUseCase

class FavoriteViewModel (userUseCase: UserUseCase) : ViewModel(){
    val favorite = userUseCase.getFavoriteUser().asLiveData()
}