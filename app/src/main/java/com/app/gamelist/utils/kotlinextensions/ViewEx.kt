package com.app.gamelist.utils.kotlinextensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// Extension to inflate layouts - may be used in adapter etc
fun ViewGroup.inflate(layoutResourceId:Int): View {
    return LayoutInflater.from(context).inflate(layoutResourceId,this,false)
}