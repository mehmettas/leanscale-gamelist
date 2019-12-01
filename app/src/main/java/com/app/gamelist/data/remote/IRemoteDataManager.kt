package com.app.gamelist.data.remote

import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import com.app.gamelist.data.remote.network.ResultWrapper

interface IRemoteDataManager {
    suspend fun getAllGames(pageSize: Int, page: Int): ResultWrapper<GameListResponse>
}