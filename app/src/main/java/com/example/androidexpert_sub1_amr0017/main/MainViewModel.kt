package com.example.androidexpert_sub1_amr0017.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.UserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

class MainViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val queryChannel = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchUsers =  queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isEmpty()){
                userUseCase.getUser()
            }else{
                userUseCase.searchUser(query)
            }
        }
        .asLiveData()

    fun searchQuery(query: String) {
        queryChannel.value = query
    }
}