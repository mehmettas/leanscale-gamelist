package com.app.gamelist.data.remote.model.gamelist.genre

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Genre(
    @SerializedName("id")
    var genreId:Int,
    @SerializedName("name")
    var genreName:String
): Serializable