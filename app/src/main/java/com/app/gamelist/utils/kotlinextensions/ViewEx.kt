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