package com.spotapp.mobile.ui.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotapp.mobile.data.repository.GameRepository
import com.spotapp.mobile.data.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    gameRepository: GameRepository
) : ViewModel() {
    private val viewModelState: MutableStateFlow<HomeViewModelState> =
        MutableStateFlow(HomeViewModelState())

    val uiState = viewModelState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                gameRepository.getUserList()
            }.onSuccess { userList ->
                Log.d("FirestoreService", "users, list: $userList")
                viewModelState.update {
                    it.copy(
                        userList = userList,
                    )
                }
            }.onFailure { throwable ->
                viewModelState.update {
                    it.copy(
                        errorMessage = throwable.message,
                    )
                }
            }

        }
    }
}
