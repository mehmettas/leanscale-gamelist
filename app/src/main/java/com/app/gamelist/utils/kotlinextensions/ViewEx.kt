package com.app.gamelist.utils.kotlinextensions

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import at.blogc.android.views.ExpandableTextView
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.utils.AppConstants
import com.app.gamelist.utils.AppConstants.PLATFORM_APPLE
import com.app.gamelist.utils.AppConstants.PLATFORM_LINUX
import com.app.gamelist.utils.AppConstants.PLATFORM_NINTENDO
import com.app.gamelist.utils.AppConstants.PLATFORM_PC
import com.app.gamelist.utils.AppConstants.PLATFORM_PLAYSTATION
import com.app.gamelist.utils.AppConstants.PLATFORM_XBOX
import kotlinx.android.synthetic.main.layout_item_game.view.*

// Extension to inflate layouts - may be used in adapter etc
fun ViewGroup.inflate(layoutResourceId:Int): View {
    return LayoutInflater.from(context).inflate(layoutResourceId,this,false)
}

fun ExpandableTextView.expandableAction(target:AppCompatImageView,resource: Drawable?) {
    if (this.isExpanded) {
        this.collapse()
        target.setImageDrawable(resource)
    } else {
        this.expand()
        target.setImageDrawable(resource)
    }
}

fun AppCompatImageView.configureRatingFigures(gameItem:GameList){
    when(gameItem.topRating){
        in AppConstants.RATE_MEH_LOW..AppConstants.RATE_MEH_HIGH ->this.icRecommendationLeveling.setImageResource(
            R.drawable.ic_meh)
        AppConstants.RATE_SUGGESTED ->this.setImageResource(R.drawable.ic_suggested)
        AppConstants.RATE_EXCEPTIONAL ->this.setImageResource(R.drawable.ic_exceptional)
    }
}

fun View.configurePlatformFigures(gameItem:GameList){
    gameItem.parentPlatforms.forEach {
        when(it.platformItem.platformId){
            PLATFORM_PC ->this.icPc.visibility = View.VISIBLE
            PLATFORM_PLAYSTATION ->this.icPlaystation.visibility = View.VISIBLE
            PLATFORM_XBOX ->this.icXbox.visibility = View.VISIBLE
            PLATFORM_NINTENDO ->this.icNintendo.visibility = View.VISIBLE
            PLATFORM_APPLE ->this.icApple.visibility = View.VISIBLE
            PLATFORM_LINUX ->this.icLinux.visibility = View.VISIBLE
        }
    }
}