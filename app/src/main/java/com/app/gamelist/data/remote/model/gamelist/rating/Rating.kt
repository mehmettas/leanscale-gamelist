package com.app.gamelist.data.remote.model.gamelist.rating

import com.app.gamelist.data.remote.model.gamelist.platform.Platform
import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("id")
    var ratingId:Int,
    @SerializedName("title")
    var ratingTitle:String,
    @SerializedName("count")
    var ratingCount:Int,
    @SerializedName("percent")
    var ratingPercent:Double
)