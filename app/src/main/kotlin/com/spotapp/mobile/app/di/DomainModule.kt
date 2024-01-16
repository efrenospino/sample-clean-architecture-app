package com.spotapp.mobile.app.di

import com.spotapp.mobile.domain.usecases.GetCurrentAppState
import com.spotapp.mobile.domain.usecases.RegisterUser
import com.spotapp.mobile.domain.usecases.UpdateUserName

class DomainModule(private val dataModule: DataModule) {

    val getCurrentAppStateUseCase: GetCurrentAppState
        get() = GetCurrentAppState(dataModule.usersRepository)

    val registerUser: RegisterUser
        get() = RegisterUser(usersRepository = dataModule.usersRepository)

    val updateUserName: UpdateUserName
        get() = UpdateUserName(usersRepository = dataModule.usersRepository)
}
