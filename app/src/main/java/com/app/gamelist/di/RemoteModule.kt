package com.app.gamelist.di

import com.app.gamelist.data.remote.service.GameListService
import com.app.gamelist.data.remote.service.ServiceClient.createWebService
import org.koin.dsl.module

val remoteModule = module {
    factory { createWebService<GameListService>() }
}