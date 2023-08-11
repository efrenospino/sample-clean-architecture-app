package com.spotapp.mobile.app.di.impl

import android.content.Context
import com.spotapp.mobile.app.di.AppStateManager
import com.spotapp.mobile.data.repository.UsersRepository
import com.spotapp.mobile.data.repository.impl.UsersRepositoryImpl
import com.spotapp.mobile.data.sources.database.AppDatabase
import com.spotapp.mobile.data.sources.preferences.UserPreferencesManager
import com.spotapp.mobile.data.sources.preferences.impl.UserPreferencesManagerImpl
import com.spotapp.mobile.data.sources.remote.RestClient

class AppStateManagerImpl(private val context: Context) : AppStateManager {

    override val localDatabase: AppDatabase
        get() = AppDatabase.get(context)

    override val restClient: RestClient
        get() = RestClient()

    override val preferencesManager: UserPreferencesManager
        get() = UserPreferencesManagerImpl(context)

    override val usersRepository: UsersRepository
        get() = UsersRepositoryImpl(
            localDatabase.userDao(),
        )

    override suspend fun hasUserSignedOn(): Boolean {
        return localDatabase.userDao().findAll().isNotEmpty()
    }

}
