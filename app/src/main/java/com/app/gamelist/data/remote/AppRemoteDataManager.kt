package com.app.gamelist.data.remote

import com.app.gamelist.data.remote.model.gamedetail.GameDetail
import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import com.app.gamelist.data.remote.network.RemoteDataException
import com.app.gamelist.data.remote.network.ResultWrapper
import com.app.gamelist.data.remote.service.GameListService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AppRemoteDataManager(
    private val gameListService: GameListService
    ): RemoteDataManager{

    override suspend fun getAllGames(pageSize: Int, page: Int): ResultWrapper<GameListResponse> =
        withContext(Dispatchers.IO) {
            resultWrapper(gameListService.getAllGamesAsync(pageSize, page))
        }

    override suspend fun getGameDetail(gameId: Int): ResultWrapper<GameDetail> =
        withContext(Dispatchers.IO) {
            resultWrapper(gameListService.getGameDetailAsync(gameId))
        }

    private suspend inline fun <reified T : Any> resultWrapper(request: Deferred<Response<T>>): ResultWrapper<T> {
        return try {
            val response = request.await()
            if (response.isSuccessful) {
                ResultWrapper.Success(response.body()!!)
            } else {
                ResultWrapper.Error(RemoteDataException(response.errorBody()))
            }
        } catch (ex: Throwable) {
            ResultWrapper.Error(RemoteDataException(ex))
        }
    }

}