package com.spotapp.mobile.data.repository

import com.spotapp.mobile.data.Result
import com.spotapp.mobile.data.sources.database.users.UserDto

interface UsersRepository {

    suspend fun newAnonymousUser(): Result<UserDto>

    suspend fun newUserWith(
        name: String,
        email: String
    ): Result<UserDto>

}
