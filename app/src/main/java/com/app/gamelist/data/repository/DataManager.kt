package com.app.gamelist.data.repository

import com.app.gamelist.data.remote.RemoteDataManager
import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import com.app.gamelist.data.remote.network.ResultWrapper

class DataManager(
    private val remoteDataManager: RemoteDataManager
):IDataManager{

    override suspend fun getAllGames(pageSize: Int, page: Int): ResultWrapper<GameListResponse> =
        remoteDataManager.getAllGames(pageSize, page)
}

