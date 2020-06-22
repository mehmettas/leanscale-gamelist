package com.app.gamelist.utils

object AppConstants {
    const val API_URL = "https://api.rawg.io/api/"
    const val REQUEST_TIMEOUT: Long = 60
    const val KEY_GAME_DATA = "KEY_GAME_DATA"

    const val ROTATE_START = 0f
    const val ROTATE_END = 360f
    const val ROTATE_DURATION: Long = 2000

    const val PLATFORM_PC = 1
    const val PLATFORM_PLAYSTATION = 2
    const val PLATFORM_XBOX = 3
    const val PLATFORM_NINTENDO = 4
    const val PLATFORM_APPLE = 5
    const val PLATFORM_LINUX = 6

    const val RATE_MEH_LOW = 1
    const val RATE_MEH_HIGH = 3
    const val RATE_SUGGESTED = 4
    const val RATE_EXCEPTIONAL = 5
}