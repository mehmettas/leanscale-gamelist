package com.app.gamelist.data.remote.service

import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IGameListService {

    @GET("games")
    fun getAllGames(@Query("page_size") pageSize: Int,
                             @Query("page") pageNumber:Int
    ): Deferred<Response<GameListResponse>>
}