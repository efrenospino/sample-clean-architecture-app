package com.spotapp.mobile.domain.usecases

import android.util.Log
import com.spotapp.mobile.data.repository.UsersRepository

class UpdateUserName(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(name: String) {
        runCatching {
            usersRepository.updateUserName(name)
        }.onSuccess {
            runCatching { usersRepository.addUserToFirestore(it) }
                .onSuccess { Log.d("UpdateUserNameCase", "User updated successfully") }
                .onFailure { throw it }
        }.onFailure { throw it }
    }
}