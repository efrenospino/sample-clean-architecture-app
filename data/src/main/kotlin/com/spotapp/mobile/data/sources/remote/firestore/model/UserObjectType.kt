package com.spotapp.mobile.data.sources.remote.firestore.model

data class UserObjectType(
    val id: String = "",
    val displayName: String = "",
    val email: String = "",
    val isAnonymous: Boolean = false,
)
