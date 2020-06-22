package com.app.gamelist.data.repository

import com.app.gamelist.data.remote.AppRemoteDataManager
import com.app.gamelist.data.remote.model.gamedetail.GameDetail
import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import com.app.gamelist.data.remote.network.ResultWrapper

class AppDataManager(
    private val appRemoteDataManager: AppRemoteDataManager
):DataManager{

    override suspend fun getAllGames(pageSize: Int, page: Int): ResultWrapper<GameListResponse> =
        appRemoteDataManager.getAllGames(pageSize, page)

    override suspend fun getGameDetail(gameId: Int): ResultWrapper<GameDetail> =
        appRemoteDataManager.getGameDetail(gameId)
}

