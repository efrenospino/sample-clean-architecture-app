package com.spotapp.mobile.data.sources.remote.firestore.model

data class Question(
    val questionText: String = "",
    val options: List<Option> = emptyList(),
    val correctOption: Option? = null,
    val userId: String = "",
)

data class Option(
    val optionText: String = "",
    val isCorrect: Boolean = false
)