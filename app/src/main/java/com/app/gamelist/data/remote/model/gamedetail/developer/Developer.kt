package com.app.gamelist.data.remote.model.gamedetail.developer

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Developer(
    @SerializedName("name")
    var name:String
): Serializable