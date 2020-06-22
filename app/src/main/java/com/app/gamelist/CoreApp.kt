package com.app.gamelist

import android.app.Application
import android.content.Context
import com.app.gamelist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@CoreApp)
            modules(appModule)
        }
    }

}