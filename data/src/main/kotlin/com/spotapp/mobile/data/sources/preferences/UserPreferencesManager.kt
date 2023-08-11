package com.spotapp.mobile.data.sources.preferences

import com.spotapp.mobile.data.sources.preferences.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesManager {

    val userPreferences: Flow<UserPreferences>

    suspend fun persist(userPreferences: UserPreferences)
}
