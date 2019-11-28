package com.app.gamelist.data.remote

import com.app.gamelist.data.remote.network.RemoteDataException
import com.app.gamelist.data.remote.network.ResultWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response

class RemoteDataManager(
    ): IRemoteDataManager {

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