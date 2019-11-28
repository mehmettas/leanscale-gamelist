package com.app.gamelist.data.repository

import com.app.gamelist.data.remote.RemoteDataManager

class DataManager(
    private val remoteDataManager: RemoteDataManager
):IDataManager {
}

