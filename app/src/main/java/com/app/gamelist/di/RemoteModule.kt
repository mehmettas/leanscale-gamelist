package com.app.gamelist.di

import com.app.gamelist.data.remote.service.IGameListService
import com.app.gamelist.data.remote.service.ServiceClient.createWebService
import org.koin.dsl.module.module

val remoteModule = module {
    factory { createWebService<IGameListService>() }
}