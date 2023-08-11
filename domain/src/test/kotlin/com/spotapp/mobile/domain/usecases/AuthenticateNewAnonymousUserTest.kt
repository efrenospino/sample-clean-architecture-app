package com.spotapp.mobile.domain.usecases

import com.spotapp.mobile.data.repository.UsersRepository
import com.spotapp.mobile.data.sources.database.users.UserDto
import com.spotapp.mobile.domain.model.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthenticateNewAnonymousUserTest {

    @Test
    fun testAuthenticateNewAnonymousUserSuccess() = runTest {

        val usersRepository = mockk<UsersRepository>()
        coEvery { usersRepository.newAnonymousUser() } returns
                com.spotapp.mobile.data.Result.Success(
                    UserDto()
                )

        val useCase = AuthenticateNewAnonymousUser(usersRepository)

        val result = useCase.invoke()

        assertTrue(result is Result.Success)
    }

    @Test
    fun testAuthenticateNewAnonymousUserFailed() = runTest {

        val usersRepository = mockk<UsersRepository>()
        coEvery { usersRepository.newAnonymousUser() } returns
                com.spotapp.mobile.data.Result.Error(
                    Exception("something went wrong")
                )

        val useCase = AuthenticateNewAnonymousUser(usersRepository)

        val result = useCase.invoke()

        assertTrue(result is Result.Error)
    }

}