package com.app.gamelist.ui.splash

import com.app.gamelist.R
import com.app.gamelist.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity(), ISplashNavigator {
    private val viewModel by viewModel<SplashViewModel>()

    override val layoutId: Int?
        get() = R.layout.activity_splash

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
    }
}
