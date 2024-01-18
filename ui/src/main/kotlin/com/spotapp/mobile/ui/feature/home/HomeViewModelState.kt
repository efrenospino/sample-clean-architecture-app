package com.spotapp.mobile.ui.feature.home

import com.spotapp.mobile.data.models.Question
import com.spotapp.mobile.data.models.User

data class HomeViewModelState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val questionList: List<Question> = emptyList(),
    val userList: List<User> = emptyList(),
    val showAddQuestionDialog: Boolean = false,
)