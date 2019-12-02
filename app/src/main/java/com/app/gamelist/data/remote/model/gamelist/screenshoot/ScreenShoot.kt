package com.app.gamelist.data.remote.model.gamelist.screenshoot

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScreenShoot(
    @SerializedName("image")
    var image:String
): Serializable