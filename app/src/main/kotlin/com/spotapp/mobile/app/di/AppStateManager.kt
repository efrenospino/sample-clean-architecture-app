package com.spotapp.mobile.app.di

import com.spotapp.mobile.data.repository.UsersRepository
import com.spotapp.mobile.data.sources.database.AppDatabase
import com.spotapp.mobile.data.sources.preferences.UserPreferencesManager
import com.spotapp.mobile.data.sources.remote.RestClient

interface AppStateManager {

    val localDatabase: AppDatabase
    val restClient: RestClient
    val preferencesManager: UserPreferencesManager
    val usersRepository: UsersRepository

    suspend fun hasUserSignedOn(): Boolean
}
