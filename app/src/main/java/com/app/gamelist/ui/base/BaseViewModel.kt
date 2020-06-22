package com.app.gamelist.ui.base

import androidx.lifecycle.ViewModel
import com.app.gamelist.data.repository.AppDataManager
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(val appDataManager: AppDataManager) : ViewModel() {
    lateinit var navigator: WeakReference<N>

    fun getNavigator(): N {
        return navigator.get()!!
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

}