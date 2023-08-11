package com.spotapp.mobile.domain.services

import android.app.Service
import android.content.Intent
import android.location.LocationListener
import android.os.IBinder

class LocationTrackerService : Service() {

    private lateinit var locationListener: LocationListener

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun myEmptyFunction() {

    }

}