package com.app.gamelist.ui.base

interface IBaseNavigator {
    fun showLoading()

    fun hideLoading()

    fun onError(errorMessage: String, errorCode: Int)
}