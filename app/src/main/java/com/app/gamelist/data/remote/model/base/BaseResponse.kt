package com.app.gamelist.data.remote.model.base

import com.google.gson.annotations.SerializedName

open class BaseResponse<out T>(
    @SerializedName("data")
    val data: T? = null
)