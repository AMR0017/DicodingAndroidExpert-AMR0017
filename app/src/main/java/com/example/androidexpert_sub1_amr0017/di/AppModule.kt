package com.example.androidexpert_sub1_amr0017.di

import com.example.androidexpert_sub1_amr0017.detail.DetailViewModel
import com.example.androidexpert_sub1_amr0017.main.MainViewModel
import com.example.core.domain.usecase.UserInteractor
import com.example.core.domain.usecase.UserUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase>{ UserInteractor(get())}
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}