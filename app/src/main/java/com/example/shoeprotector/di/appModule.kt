package com.example.shoeprotector.di

import com.example.shoeprotector.viewmodel.CardViewModel
import com.example.shoeprotector.viewmodel.LoginViewModel
import com.example.shoeprotector.viewmodel.RegisterCardViewModel
import com.example.shoeprotector.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterCardViewModel(get(), get()) }
    viewModel { CardViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}