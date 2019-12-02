package com.app.gamelist.ui.gamedetail

import com.app.gamelist.R
import com.app.gamelist.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameDetailActivity : BaseActivity(), IGameDetailNavigator {
    private val viewModel by viewModel<GameDetailViewModel>()

    override val layoutId: Int?
        get() = R.layout.activity_game_detail

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
    }
}
