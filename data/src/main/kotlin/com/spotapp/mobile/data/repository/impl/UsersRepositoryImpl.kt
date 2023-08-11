package com.spotapp.mobile.data.repository.impl

import android.database.sqlite.SQLiteException
import com.spotapp.mobile.data.Result
import com.spotapp.mobile.data.repository.UsersRepository
import com.spotapp.mobile.data.sources.database.users.UserDao
import com.spotapp.mobile.data.sources.database.users.UserDto
import com.spotapp.mobile.data.sources.database.users.UserInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepositoryImpl(private val userDao: UserDao) : UsersRepository {

    override suspend fun newAnonymousUser(): Result<UserDto> =
        withContext(Dispatchers.IO) {
            try {
                Result.Success(UserDto().also { userDao.save(it) })
            } catch (e: SQLiteException) {
                Result.Error(e)
            }
        }

    override suspend fun newUserWith(
        name: String,
        email: String
    ): Result<UserDto> =
        withContext(Dispatchers.IO) {
            try {
                Result.Success(
                    UserDto(
                        userInfo = UserInfoDto(
                            name = name, email = email,
                        ),
                    ).also {
                        userDao.save(it)
                    }
                )
            } catch (e: SQLiteException) {
                Result.Error(e)
            }
        }

}
