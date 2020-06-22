package com.app.gamelist.di

import com.app.gamelist.data.remote.RemoteDataManager
import com.app.gamelist.data.repository.DataManager
import org.koin.dsl.module

val managerModule = module {
    single { DataManager(get()) }
    single { RemoteDataManager(get()) }
}