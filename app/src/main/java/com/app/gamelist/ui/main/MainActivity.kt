package com.app.gamelist.ui.main

import android.view.Gravity
import android.view.View
import androidx.lifecycle.Observer
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.data.remote.model.gamelist.genre.Genre
import com.app.gamelist.ui.base.BaseActivity
import com.app.gamelist.ui.main.adapter.ChipAdapter
import com.app.gamelist.ui.main.adapter.GameListAdapter
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item_game.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), IMainNavigator,
    GameListAdapter.CustomGameListListener{

    private val viewModel by viewModel<MainViewModel>()
    private val gameListAdapter by lazy {
        GameListAdapter(arrayListOf(), this)
    }


    private var gameListData: ArrayList<GameList>? = arrayListOf()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()

        recyclerViewGames.setHasFixedSize(true)
        recyclerViewGames.adapter = gameListAdapter
        configureGenresRecyclerView()

        viewModel.getAllGames(10,1)
    }

    private fun configureGenresRecyclerView() {

    }

    private fun observeViewModel(){
        viewModel.allGames.observe(this, Observer {
            gameListData = it.results
            gameListAdapter.addData(gameListData!!)
        })

    }

    override fun initListener() {
    }

    override fun onGameItemSelected(itemSession: View) {}
}
