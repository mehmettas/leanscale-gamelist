package com.app.gamelist

import android.app.Application
import android.content.Context
import com.app.gamelist.di.appModule
import org.koin.android.ext.android.startKoin

class CoreApp: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        configureDependencyInjection()
    }

    // Start dependency injector on application first run
    private fun configureDependencyInjection() {
        startKoin(this, appModule)
    }

}