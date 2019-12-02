package com.app.gamelist.ui.main

import androidx.lifecycle.MutableLiveData
import com.app.gamelist.data.remote.model.gamelist.GameListResponse
import com.app.gamelist.data.remote.network.ResultWrapper
import com.app.gamelist.data.repository.DataManager
import com.app.gamelist.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(dataManager: DataManager): BaseViewModel<IMainNavigator>(dataManager) {
    val allGames: MutableLiveData<GameListResponse> = MutableLiveData()

    fun getAllGames(pageSize: Int, page:Int) {
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getAllGames(pageSize,page) }) {
                is ResultWrapper.Success -> {
                    allGames.value = result.data
                }
                is ResultWrapper.Error -> {
                    val x = 0
                }
            }
        }
    }
}