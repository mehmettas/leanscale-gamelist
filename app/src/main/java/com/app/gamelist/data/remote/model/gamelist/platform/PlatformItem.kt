package com.app.gamelist.data.remote.model.gamelist.platform

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlatformItem(
    @SerializedName("id")
    var platformId:Int,
    @SerializedName("name")
    var platformName:String,
    @SerializedName("slug")
    var slug:String): Serializable