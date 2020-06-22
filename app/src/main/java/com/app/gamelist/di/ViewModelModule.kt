package com.app.gamelist.di

import com.app.gamelist.ui.gamedetail.GameDetailViewModel
import com.app.gamelist.ui.main.MainViewModel
import com.app.gamelist.ui.splash.SplashViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { GameDetailViewModel(get()) }
}