package com.spotapp.mobile.app.navigation

import com.spotapp.mobile.app.di.AppStateManager

typealias Route = String

object Destinations {

    fun home(): Route = "/home"

    fun auth(): Route = "/auth"

    suspend fun startDestination(appStateManager: AppStateManager): String {
        if (appStateManager.hasUserSignedOn()) {
            return home()
        }
        return auth()
    }
}
