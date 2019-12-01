package com.app.gamelist.ui.main

import android.view.View
import com.app.gamelist.R
import com.app.gamelist.ui.base.BaseActivity
import com.app.gamelist.ui.main.adapter.GameListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), IMainNavigator, GameListAdapter.CustomGameListListener {
    private val viewModel by viewModel<MainViewModel>()

    private val gameListAdapter by lazy {
        GameListAdapter(arrayListOf(), this)
    }

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()

        recyclerViewGames.setHasFixedSize(true)
        recyclerViewGames.adapter = gameListAdapter
        var sampleData: ArrayList<String> = arrayListOf()
        sampleData.add("")
        sampleData.add("")
        sampleData.add("")
        sampleData.add("")
        sampleData.add("")
        gameListAdapter.addData(sampleData)

    }

    private fun observeViewModel(){

    }

    override fun initListener() {
    }

    override fun onGameItemSelected(itemSession: View) {
    }
}
