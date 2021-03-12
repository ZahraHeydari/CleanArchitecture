package com.android.photogallery.core

import android.app.Application
import androidx.multidex.MultiDex
import com.android.photogallery.di.appModule
import com.android.photogallery.di.databaseModule
import com.android.photogallery.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this) //To avoid build error - for app with over 64k methods

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(appModule, networkModule, databaseModule))
        }
    }
}