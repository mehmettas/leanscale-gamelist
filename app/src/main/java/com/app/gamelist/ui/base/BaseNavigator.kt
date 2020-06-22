package com.app.gamelist.ui.base

interface BaseNavigator {
    fun showLoading()

    fun hideLoading()

    fun onError(errorMessage: String, errorCode: Int)
}