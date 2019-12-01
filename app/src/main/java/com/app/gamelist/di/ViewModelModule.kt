package com.app.gamelist.di

import com.app.gamelist.ui.main.MainViewModel
import com.app.gamelist.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
}