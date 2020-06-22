package com.app.gamelist.ui.main

import androidx.lifecycle.MutableLiveData
import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import com.app.gamelist.data.remote.network.ResultWrapper
import com.app.gamelist.data.repository.AppDataManager
import com.app.gamelist.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(appDataManager: AppDataManager): BaseViewModel<MainNavigator>(appDataManager) {
    val allGames: MutableLiveData<GameListResponse> = MutableLiveData()

    fun getAllGames(pageSize: Int, page:Int) {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { appDataManager.getAllGames(pageSize,page) }) {
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    allGames.value = result.data
                }
                is ResultWrapper.Error -> {
                }
            }
        }
    }
}