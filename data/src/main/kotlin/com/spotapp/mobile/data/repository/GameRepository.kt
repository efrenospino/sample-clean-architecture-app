package com.spotapp.mobile.data.repository

import com.spotapp.mobile.data.models.User
import com.spotapp.mobile.data.sources.remote.firestore.FirestoreService
import com.spotapp.mobile.data.sources.remote.firestore.model.Question

class GameRepository(private val firestoreService: FirestoreService) {
    suspend fun getUserList(): List<User> = firestoreService.getUserList().map {
        User(
            name = it.displayName,
            email = it.email,
            isAnonymous = it.isAnonymous
        )
    }
}