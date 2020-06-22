package com.app.gamelist.ui.gamedetail

import androidx.lifecycle.MutableLiveData
import com.app.gamelist.data.remote.model.gamedetail.GameDetail
import com.app.gamelist.data.remote.network.ResultWrapper
import com.app.gamelist.data.repository.AppDataManager
import com.app.gamelist.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetailViewModel(appDataManager: AppDataManager): BaseViewModel<GameDetailNavigator>(appDataManager) {
    val gameDetail: MutableLiveData<GameDetail> = MutableLiveData()

    fun getGameDetail(pageId:Int) {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { appDataManager.getGameDetail(pageId) }) {
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    gameDetail.value = result.data
                }
                is ResultWrapper.Error -> {
                }
            }
        }
    }
}