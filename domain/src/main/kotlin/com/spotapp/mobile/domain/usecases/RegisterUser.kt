package com.spotapp.mobile.domain.usecases

import android.util.Log
import com.spotapp.mobile.data.models.User
import com.spotapp.mobile.data.repository.UsersRepository

class RegisterUser(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(email: String, password: String) {
        runCatching {
            usersRepository.signUpUserFirebase(email, password)
        }.onSuccess { authResult ->
            runCatching {
                usersRepository.addUserToFirestore(
                    User(
                        id = authResult.user?.uid,
                        name = authResult.user?.displayName ?: "",
                        email = authResult.user?.email,
                        isAnonymous = authResult.user?.isAnonymous ?: false
                    )
                )
            }.onSuccess {
                Log.d("RegisterUserUseCase", "User registered successfully")
            }.onFailure { throw it }
        }.onFailure { throw it }
    }
}