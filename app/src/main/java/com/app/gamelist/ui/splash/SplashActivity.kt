package com.app.gamelist.ui.splash

import android.content.Intent
import androidx.lifecycle.Observer
import com.app.gamelist.MainActivity
import com.app.gamelist.R
import com.app.gamelist.ui.base.BaseActivity
import com.app.gamelist.utils.kotlinextensions.launchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity(), ISplashNavigator {
    private val viewModel by viewModel<SplashViewModel>()

    override val layoutId: Int?
        get() = R.layout.activity_splash

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.isFinished.observe(this, Observer {       // Observe liveData, when it changes change the page
            if (it)
                launchActivity<MainActivity>{
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
        })
    }

    override fun initListener() {
    }
}
