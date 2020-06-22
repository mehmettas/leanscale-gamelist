package com.app.gamelist.utils.kotlinextensions

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import at.blogc.android.views.ExpandableTextView

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