package com.spotapp.mobile.app.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.spotapp.mobile.data.repository.GameRepository
import com.spotapp.mobile.data.repository.UsersRepository
import com.spotapp.mobile.data.sources.database.AppDatabase
import com.spotapp.mobile.data.sources.preferences.UserPreferencesManager
import com.spotapp.mobile.data.sources.preferences.impl.UserPreferencesManagerImpl
import com.spotapp.mobile.data.sources.remote.RestClient
import com.spotapp.mobile.data.sources.remote.firestore.FirestoreService

class DataModule(private val systemModule: SystemModule) {

    val appDatabase: AppDatabase
        get() = AppDatabase.get(systemModule.applicationContext)

    val restClient: RestClient
        get() = RestClient()

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    val firestoreService: FirestoreService = FirestoreService(firebaseAuth)

    private val userPreferencesManager: UserPreferencesManager
        get() = UserPreferencesManagerImpl.getInstance(systemModule.applicationContext)

    val usersRepository: UsersRepository
        get() = UsersRepository(
            auth = firebaseAuth,
            userPreferencesManager = userPreferencesManager,
            firestoreService = firestoreService
        )

    val gameRepository: GameRepository
        get() = GameRepository(firestoreService)

}
