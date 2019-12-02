package com.app.gamelist.data.remote.model.gamelist.platform

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Platform(
    @SerializedName("platform")
    var platformItem: PlatformItem
): Serializable
