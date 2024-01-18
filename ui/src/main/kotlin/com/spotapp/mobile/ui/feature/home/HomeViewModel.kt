package com.spotapp.mobile.ui.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotapp.mobile.data.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {
    private val viewModelState: MutableStateFlow<HomeViewModelState> =
        MutableStateFlow(HomeViewModelState())

    val uiState = viewModelState.asStateFlow()

    init {
        getUserList()
        getQuestions()
    }

    private fun getUserList() {
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

    private fun getQuestions() {
        viewModelScope.launch {
            runCatching {
                gameRepository.getQuestionList()
            }.onSuccess { questionList ->
                Log.d("FirestoreService", "questions, list: $questionList")
                viewModelState.update {
                    it.copy(
                        questionList = questionList,
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

    fun playGame() {
        runCatching { gameRepository.registerUserScore() }
            .onSuccess {
                Log.d("HomeViewModel", "registerUserScore 👍")
            }.onFailure {
                Log.d("HomeViewModel", "error => ${it.message}")
            }
    }

    fun onAddQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                gameRepository.addQuestions(generateQuestions())
            }.onSuccess {
                viewModelState.update {
                    it.copy(
                        showAddQuestionDialog = true
                    )
                }
                getQuestions()
            }.onFailure { throwable ->
                viewModelState.update {
                    it.copy(
                        errorMessage = throwable.message
                    )
                }
            }
        }
    }

    fun cleanError() {
        viewModelState.update { it.copy(errorMessage = null) }
    }

    fun hideQuestionDialog() {
        viewModelState.update { it.copy(showAddQuestionDialog = false) }
    }
}
