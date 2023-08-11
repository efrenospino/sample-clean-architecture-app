package com.spotapp.mobile.app

import android.app.Application
import com.spotapp.mobile.app.di.AppStateManager
import com.spotapp.mobile.app.di.impl.AppStateManagerImpl

class SpotApplication : Application() {

    lateinit var appStateManager: AppStateManager

    override fun onCreate() {
        super.onCreate()
        appStateManager = AppStateManagerImpl(this)
    }
}
