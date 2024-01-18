package com.spotapp.mobile.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.spotapp.mobile.data.models.Option
import com.spotapp.mobile.data.models.User
import com.spotapp.mobile.data.sources.remote.firestore.FirestoreService
import com.spotapp.mobile.data.models.Question
import com.spotapp.mobile.data.sources.remote.firebasedatabase.FDatabaseService
import com.spotapp.mobile.data.sources.remote.firestore.model.Question as firestoreQuestionModel
import com.spotapp.mobile.data.sources.remote.firestore.model.Option as firestoreOptionModel

class GameRepository(
    private val firestoreService: FirestoreService,
    private val firebaseDB: FDatabaseService,
    auth: FirebaseAuth,
) {

    private val currentUser = auth.currentUser
    suspend fun getUserList(): List<User> = firestoreService.getUserList().map {
        User(
            name = it.displayName,
            email = it.email,
            isAnonymous = it.isAnonymous
        )
    }

    suspend fun getQuestionList(): List<Question> =
        firestoreService.getQuestionsList().map { question ->
            Question(
                questionText = question.questionText,
                options = question.options.map { option ->
                    Option(
                        id = option.id,
                        optionText = option.optionText,
                        isCorrect = option.isCorrect
                    )
                },
                correctOption = Option(
                    id = question.correctOption?.id ?: 0,
                    optionText = question.correctOption?.optionText ?: "",
                    isCorrect = question.correctOption?.isCorrect ?: false
                )
            )
        }

    fun registerUserScore() {
        firebaseDB.setUserScore()
    }

    suspend fun addQuestions(questions: List<Question>) {
        runCatching {
            questions.forEach { question ->
                firestoreService.addQuestion(
                    firestoreQuestionModel(
                        questionText = question.questionText,
                        options = question.options.map { option ->
                            firestoreOptionModel(
                                id = option.id,
                                optionText = option.optionText,
                                isCorrect = option.isCorrect
                            )
                        },
                        correctOption = firestoreOptionModel(
                            id = question.correctOption.id,
                            optionText = question.correctOption.optionText,
                            isCorrect = question.correctOption.isCorrect
                        ),
                        userId = currentUser!!.uid
                    )
                )
            }
        }.onSuccess {
            Log.d("addQuestions", "Questions added successfully")
        }.onFailure {
            Log.d("addQuestions", "Error! ${it.message}")
        }
    }
}