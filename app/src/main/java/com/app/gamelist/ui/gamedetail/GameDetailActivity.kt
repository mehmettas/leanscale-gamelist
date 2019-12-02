package com.app.gamelist.ui.gamedetail

import android.view.Gravity
import androidx.lifecycle.Observer
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.ui.base.BaseActivity
import com.app.gamelist.ui.main.adapter.ChipAdapter
import com.app.gamelist.utils.AppConstants.KEY_GAME_DATA
import com.app.gamelist.utils.kotlinextensions.load
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_game_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameDetailActivity : BaseActivity(), IGameDetailNavigator {
    private val viewModel by viewModel<GameDetailViewModel>()
    private lateinit var gameItem:GameList

    override val layoutId: Int?
        get() = R.layout.activity_game_detail

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()

        if (intent.extras!=null){
            gameItem = intent.getSerializableExtra(KEY_GAME_DATA) as GameList
            viewModel.getGameDetail(gameItem.id)
        }
    }

    private fun observeViewModel(){
        viewModel.gameDetail.observe(this, Observer {
            val gameData = it
            txtGameName.text = gameData?.gameName
            txtDeveloperAndReleaseDate.text =  "By ${gameData!!.developers[0].name}, ${gameData!!.releasedDate}"
            imgGame.load(gameItem.backgroundImage)

            when(gameItem.topRating){
                in 1..3->icRecommendationLeveling.setImageResource(R.drawable.ic_meh)
                4->icRecommendationLeveling.setImageResource(R.drawable.ic_suggested)
                5->icRecommendationLeveling.setImageResource(R.drawable.ic_exceptional)
            }

            val chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
                .setScrollingEnabled(true)
                .setGravityResolver { Gravity.NO_GRAVITY }
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                .build()

            recyclerviewGenres.apply {
                setHasFixedSize(true)
                layoutManager = chipsLayoutManager
                adapter = ChipAdapter(gameItem.genres)
                addItemDecoration(
                    SpacingItemDecoration(
                        resources.getDimensionPixelOffset(R.dimen.chip_margin),
                        resources.getDimensionPixelOffset(R.dimen.chip_margin)
                    )
                )
            }
        })


    }

    override fun initListener() {
        imgBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}
