package com.app.gamelist.ui.gamedetail

import android.view.Gravity
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamedetail.GameDetail
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.ui.base.BaseActivity
import com.app.gamelist.ui.main.adapter.ChipAdapter
import com.app.gamelist.utils.AppConstants.KEY_GAME_DATA
import com.app.gamelist.utils.kotlinextensions.load
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.layout_chart.*
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
            setContent(gameData)
        })


    }

    private fun setContent(gameData: GameDetail?) {
        setTopViews(gameData)
        setGenres()
        setChartData(gameData)
        setRatingCountValues(gameData)
    }

    private fun setRatingCountValues(gameData: GameDetail?) {
        var ratingException = (gameData?.ratings?.find {
            it.ratingId == 5
        })
        txtExceptional.text = ratingException?.ratingCount.toString()

        var ratingSkip = (gameData?.ratings?.find {
            it.ratingId == 1
        })
        txtSkip.text = ratingSkip?.ratingCount.toString()

        var ratingRecommended = (gameData?.ratings?.find {
            it.ratingId == 4
        })
        txtRecommended.text = ratingRecommended?.ratingCount.toString()

        var ratingMeh = (gameData?.ratings?.find {
            it.ratingId == 3
        })
        txtMeh.text = ratingMeh?.ratingCount.toString()
    }

    private fun setChartData(gameData: GameDetail?) {
        gameData?.ratings?.forEach {
            var value: Float = it.ratingPercent.toFloat() / 100
            val param = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                value
            )

            when (it.ratingId) {
                5 -> imgGreen.layoutParams = param
                4 -> imgBlue.layoutParams = param
                1 -> imgSkip.layoutParams = param
                3 -> imgOrange.layoutParams = param
            }
        }
    }

    private fun setTopViews(gameData: GameDetail?) {
        txtGameName.text = gameData?.gameName
        txtDeveloperAndReleaseDate.text =
            "By ${gameData!!.developers[0].name}, ${gameData!!.releasedDate}"
        imgGame.load(gameItem.backgroundImage)

        when (gameItem.topRating) {
            in 1..3 -> icRecommendationLeveling.setImageResource(R.drawable.ic_meh)
            4 -> icRecommendationLeveling.setImageResource(R.drawable.ic_suggested)
            5 -> icRecommendationLeveling.setImageResource(R.drawable.ic_exceptional)
        }
    }

    private fun setGenres() {
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
    }

    override fun initListener() {
        imgBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}
