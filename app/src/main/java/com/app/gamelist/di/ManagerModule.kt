package com.app.gamelist.di

import com.app.gamelist.data.remote.AppRemoteDataManager
import com.app.gamelist.data.repository.AppDataManager
import org.koin.dsl.module

val managerModule = module {
    single { AppDataManager(get()) }
    single { AppRemoteDataManager(get()) }
}