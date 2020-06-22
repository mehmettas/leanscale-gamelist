package com.app.gamelist.ui.splash

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.app.gamelist.data.repository.AppDataManager
import com.app.gamelist.ui.base.BaseViewModel

class SplashViewModel(appDataManager: AppDataManager): BaseViewModel<SplashNavigator>(appDataManager) {
    val isFinished = MutableLiveData<Boolean>()

    // As soon as class initialized, start the timer, and do a little wait for the splash
    init {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                isFinished.postValue(true)
            }
        }.start()
    }

}