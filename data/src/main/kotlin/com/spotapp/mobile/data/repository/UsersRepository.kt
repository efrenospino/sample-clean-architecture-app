package com.spotapp.mobile.data.repository

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.spotapp.mobile.data.models.User
import com.spotapp.mobile.data.sources.preferences.PreferencesKeys
import com.spotapp.mobile.data.sources.preferences.UserPreferencesManager
import com.spotapp.mobile.data.sources.preferences.model.SessionState
import com.spotapp.mobile.data.sources.remote.firestore.FirestoreService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UsersRepository(
    private val auth: FirebaseAuth,
    private val userPreferencesManager: UserPreferencesManager,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val firestoreService: FirestoreService,
) {

    suspend fun signUpUserFirebase(
        email: String,
        password: String
    ): AuthResult {
        return withContext(coroutineDispatcher) {
            runCatching {
                auth.createUserWithEmailAndPassword(email, password).await()
            }.fold(
                onSuccess = {
                    Log.d("signUpUserFirebase", "Success!")
                    it
                }, onFailure = {
                    throw it
                }
            )
        }
    }

    suspend fun getUserCredentials(): Triple<Boolean, String?, String?> {
        return userPreferencesManager.userPreferences.first().let {
            Triple(it.rememberCredentials, it.userEmail, it.userPassword)
        }
    }

    suspend fun signInUserFirebase(
        email: String,
        password: String,
        rememberCredentials: Boolean = false
    ) {
        withContext(Dispatchers.IO) {
            runCatching {
                auth.signInWithEmailAndPassword(email, password).await()
            }.fold(
                onSuccess = {
                    userPreferencesManager.persist {
                        it[PreferencesKeys.sessionStatus] = SessionState.LOGGED_IN.name
                        it[PreferencesKeys.rememberCredentials] = rememberCredentials
                        if (rememberCredentials) {
                            it[PreferencesKeys.userEmail] = email
                            it[PreferencesKeys.userPassword] = password
                        } else {
                            it.remove(PreferencesKeys.userEmail)
                            it.remove(PreferencesKeys.userPassword)
                        }
                    }
                }, onFailure = {
                    throw it
                }
            )
        }
    }

    suspend fun signInAnonymously() {
        withContext(Dispatchers.IO) {
            runCatching {
                auth.signInAnonymously().await()
            }.onSuccess {
                userPreferencesManager.persist {
                    it[PreferencesKeys.sessionStatus] = SessionState.LOGGED_IN.name
                }
            }.onFailure {
                throw it
            }
        }
    }

    fun signOutFirebase() {
        runCatching {
            auth.signOut()
        }.fold(
            onSuccess = {
                Log.d("signOutFirebase", "signOut successful!")
            }, onFailure = {
                throw it
            }
        )
    }

    fun getUserInformation(): User? {
        return auth.currentUser?.let {
            User(
                email = it.email,
                name = it.displayName,
                isAnonymous = it.isAnonymous,
            )
        }
    }

    suspend fun updateUserName(name: String): User {
        return withContext(Dispatchers.IO) {
            runCatching {
                userProfileChangeRequest {
                    displayName = name
                }.let {
                    auth.currentUser?.updateProfile(it)?.await()
                }
            }.fold(
                onSuccess = {
                    User(
                        id = auth.currentUser?.uid,
                        name = name,
                        email = auth.currentUser?.email,
                        isAnonymous = auth.currentUser?.isAnonymous ?: false
                    )
                },
                onFailure = {
                    throw it
                }
            )
        }
    }

    suspend fun addUserToFirestore(user: User) {
        withContext(Dispatchers.IO) {
            runCatching {
                firestoreService.addUser(user)
            }.onSuccess {
                Log.d("addUserToFirestore", "successful!")
            }.onFailure {
                throw it
            }
        }
    }

    suspend fun updateUserPassword(oldPassWord: String, newPassword: String) {
        withContext(Dispatchers.IO) {
            runCatching {
                auth.currentUser!!.reauthenticate(
                    EmailAuthProvider
                        .getCredential(auth.currentUser!!.email!!, oldPassWord)
                ).await()
            }.onFailure {
                Log.d("updateUserEmail", "Failure! reauthenticate - ${it.message}")
                throw it
            }.onSuccess {
                Log.d("updateUserEmail", "Success! reauthenticate")
                updateUserPassword(newPassword)
            }
        }
    }

    private suspend fun updateUserPassword(newPassword: String) {
        runCatching {
            auth.currentUser!!.updatePassword(newPassword).await()
        }.onSuccess {
            Log.d("updateUserEmail", "Success! updateUserPassword")
        }.onFailure {
            throw it
        }
    }

    fun hasUserSignedOn(): Boolean = auth.currentUser != null
}
