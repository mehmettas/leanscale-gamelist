package com.app.gamelist.ui.gamedetail

import android.view.Gravity
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamedetail.GameDetail
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.ui.base.BaseActivity
import com.app.gamelist.ui.gamedetail.adapter.ScreenShotPagerAdapter
import com.app.gamelist.ui.main.adapter.ChipAdapter
import com.app.gamelist.utils.AppConstants.KEY_GAME_DATA
import com.app.gamelist.utils.AppConstants.PLATFORM_APPLE
import com.app.gamelist.utils.AppConstants.PLATFORM_LINUX
import com.app.gamelist.utils.AppConstants.PLATFORM_NINTENDO
import com.app.gamelist.utils.AppConstants.PLATFORM_PC
import com.app.gamelist.utils.AppConstants.PLATFORM_PLAYSTATION
import com.app.gamelist.utils.AppConstants.PLATFORM_XBOX
import com.app.gamelist.utils.AppConstants.RATE_EXCEPTIONAL
import com.app.gamelist.utils.AppConstants.RATE_MEH_HIGH
import com.app.gamelist.utils.AppConstants.RATE_MEH_LOW
import com.app.gamelist.utils.AppConstants.RATE_SUGGESTED
import com.app.gamelist.utils.kotlinextensions.expandableAction
import com.app.gamelist.utils.kotlinextensions.load
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.layout_chart.*
import kotlinx.android.synthetic.main.layout_screenshoots.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameDetailActivity : BaseActivity(), GameDetailNavigator {
    private val viewModel by viewModel<GameDetailViewModel>()
    private lateinit var gameItem:GameList
    private var screenShotPagerAdapter:ScreenShotPagerAdapter?=null

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
            setViewPagerScreenShoot()
        }
    }

    private fun setViewPagerScreenShoot() {
        screenShotPagerAdapter = ScreenShotPagerAdapter(gameItem.screenShoots, this)
        viewPagerScreenShoot.adapter = screenShotPagerAdapter
        indicatorScreenShoot.setViewPager(viewPagerScreenShoot)
    }

    private fun observeViewModel(){
        viewModel.gameDetail.observe(this, Observer {
            val gameData = it
            setContent(gameData)
            setPlatformsData()
        })
    }

    private fun setPlatformsData() {
        var platformString = ""
        gameItem.parentPlatforms.forEach {
            when (it.platformItem.platformId) {
                PLATFORM_PC -> {
                    icPc.visibility = View.VISIBLE
                    platformString+="${it.platformItem.platformName}, "
                }
                PLATFORM_PLAYSTATION -> {
                    icPlaystation.visibility = View.VISIBLE
                    platformString+="${it.platformItem.platformName}, "
                }
                PLATFORM_XBOX -> {
                    icXbox.visibility = View.VISIBLE
                    platformString+="${it.platformItem.platformName}, "
                }
                PLATFORM_NINTENDO -> {
                    icNintendo.visibility = View.VISIBLE
                    platformString+="${it.platformItem.platformName}, "
                }
                PLATFORM_APPLE -> {
                    icApple.visibility = View.VISIBLE
                    platformString+="${it.platformItem.platformName}, "
                }
                PLATFORM_LINUX -> {
                    icLinux.visibility = View.VISIBLE
                    platformString+="${it.platformItem.platformName}, "
                }
            }
        }
        txtPlatforms.text = platformString
    }

    private fun setContent(gameData: GameDetail?) {
        setTopViews(gameData)
        setGenres()
        setChartData(gameData)
        setRatingCountValues(gameData)
    }

    private fun setRatingCountValues(gameData: GameDetail?) {
        val ratingException = (gameData?.ratings?.find {
            it.ratingId == RATE_EXCEPTIONAL
        })
        txtExceptional.text = ratingException?.ratingCount.toString()

        val ratingSkip = (gameData?.ratings?.find {
            it.ratingId == RATE_MEH_LOW
        })
        txtSkip.text = ratingSkip?.ratingCount.toString()

        val ratingRecommended = (gameData?.ratings?.find {
            it.ratingId == RATE_SUGGESTED
        })
        txtRecommended.text = ratingRecommended?.ratingCount.toString()

        val ratingMeh = (gameData?.ratings?.find {
            it.ratingId == RATE_MEH_HIGH
        })
        txtMeh.text = ratingMeh?.ratingCount.toString()
    }

    private fun setChartData(gameData: GameDetail?) {
        gameData?.ratings?.forEach {
            val value: Float = it.ratingPercent.toFloat() / 100
            val param = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                value
            )

            when (it.ratingId) {
                RATE_EXCEPTIONAL -> imgGreen.layoutParams = param
                RATE_SUGGESTED -> imgBlue.layoutParams = param
                RATE_MEH_LOW -> imgSkip.layoutParams = param
                RATE_MEH_HIGH -> imgOrange.layoutParams = param
            }
        }
    }

    private fun setTopViews(gameData: GameDetail?) {
        txtGameName.text = gameData?.gameName
        txtDeveloperAndReleaseDate.text =
            "By ${gameData!!.developers[0].name}, ${gameData!!.releasedDate}"
        imgGame.load(gameItem.backgroundImage)

        when (gameItem.topRating) {
            in RATE_MEH_LOW..RATE_MEH_HIGH -> icRecommendationLeveling.setImageResource(R.drawable.ic_meh)
            RATE_SUGGESTED -> icRecommendationLeveling.setImageResource(R.drawable.ic_suggested)
            RATE_EXCEPTIONAL -> icRecommendationLeveling.setImageResource(R.drawable.ic_exceptional)
        }
        txtAboutExpandable.setInterpolator(OvershootInterpolator())
        txtAboutExpandable.text = gameData.description
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

        imgShowMoreAboutContent.setOnClickListener {
            txtAboutExpandable.expandableAction(imgShowMoreAboutContent,getDrawable(R.drawable.ic_arrow_down))
        }
    }
}