package com.spotapp.mobile.data.sources.preferences.impl

import android.content.Context
import com.spotapp.mobile.data.sources.preferences.UserPreferencesManager
import com.spotapp.mobile.data.sources.preferences.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class UserPreferencesManagerImpl(context: Context) : UserPreferencesManager {

    override val userPreferences: Flow<UserPreferences>
        get() = emptyFlow()

    override suspend fun persist(userPreferences: UserPreferences) {

    }
}
