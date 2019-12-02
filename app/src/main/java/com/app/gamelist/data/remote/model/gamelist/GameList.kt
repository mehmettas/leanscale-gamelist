package com.app.gamelist.data.remote.model.gamelist

import com.app.gamelist.data.remote.model.gamelist.genre.Genre
import com.app.gamelist.data.remote.model.gamelist.platform.Platform
import com.app.gamelist.data.remote.model.gamelist.rating.Rating
import com.app.gamelist.data.remote.model.gamelist.screenshoot.ScreenShoot
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GameList(
    @SerializedName("id")
    var id:Int,

    @SerializedName("name")
    var gameName:String,

    @SerializedName("released")
    var releasedDate:String,

    @SerializedName("background_image")
    var backgroundImage:String,

    @SerializedName("rating_top")
    var topRating:Int,

    @SerializedName("ratings")
    var ratings: ArrayList<Rating>,

    @SerializedName("added")
    var addedCount:Int,

    @SerializedName("parent_platforms")
    var parentPlatforms:ArrayList<Platform>,

    @SerializedName("genres")
    var genres:ArrayList<Genre>,

    @SerializedName("short_screenshots")
    var screenShoots:ArrayList<ScreenShoot>
): Serializable