package com.spotapp.mobile.data.models

data class Question(
    val questionText: String,
    val options: List<Option>,
    val correctOption: Option,
)

data class Option(
    val id: Int,
    val optionText: String,
    val isCorrect: Boolean
)
