package com.app.gamelist.data.remote.service

import com.app.gamelist.data.remote.model.gamedetail.GameDetail
import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameListService {

    @GET("games")
    fun getAllGamesAsync(@Query("page_size") pageSize: Int,
                         @Query("page") pageNumber:Int
    ): Deferred<Response<GameListResponse>>

    @GET("games/{id}")
    fun getGameDetailAsync(@Path("id") gameId: Int): Deferred<Response<GameDetail>>

}