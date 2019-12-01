package com.app.gamelist.ui.main

import com.app.gamelist.R
import com.app.gamelist.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), IMainNavigator {
    private val viewModel by viewModel<MainViewModel>()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
    }
}
