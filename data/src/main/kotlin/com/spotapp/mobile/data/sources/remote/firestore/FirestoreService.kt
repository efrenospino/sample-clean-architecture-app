package com.spotapp.mobile.data.sources.remote.firestore

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.spotapp.mobile.data.models.User
import com.spotapp.mobile.data.sources.remote.firestore.model.Question
import com.spotapp.mobile.data.sources.remote.firestore.model.UserObjectType
import kotlinx.coroutines.tasks.await

class FirestoreService(private val auth: FirebaseAuth) {
    private val firestoreDB: FirebaseFirestore = Firebase.firestore
    private val activeSnapshotListenerRegistrations: MutableList<ListenerRegistration> =
        mutableListOf()

    private val usersPath = "test/global/users"
    private val questionsPath = "game/questions"

    fun subscribeToRealtimeUpdates() {
        val collectionPaths = mutableListOf(
            usersPath,
            questionsPath,
        )
        auth.currentUser?.let {
            for (path in collectionPaths) {
                val collectionReference = firestoreDB.collection("game")
                val listenerRegistration =
                    collectionReference.addSnapshotListener(MetadataChanges.INCLUDE) { _, exception ->
                        exception?.let { firestoreException ->
                            Log.d("FirestoreService", "$firestoreException")
                            return@addSnapshotListener
                        }
                    }

                activeSnapshotListenerRegistrations.add(listenerRegistration)
            }
        }
    }

    suspend fun getUserList(): List<UserObjectType> {
        return runCatching {
            firestoreDB.collection(usersPath).orderBy("email").get(Source.SERVER).await()
                .toObjects(UserObjectType::class.java)

        }.fold(onSuccess = {
            Log.d("FirestoreService", "getUserList success, list: ${it.size}")
            it
        }, onFailure = {
            Log.d("FirestoreService", "getUserList failure, error: ${it.message}")
            throw it
        })
    }

    suspend fun addUser(user: User) {
        val newUser = hashMapOf(
            "displayName" to user.name,
            "email" to user.email,
            "isAnonymous" to user.isAnonymous,
        )
        runCatching {
            firestoreDB.collection(usersPath).document(user.id!!).set(newUser).await()
        }.onSuccess {
            Log.d("FirestoreService", "addUser success, document reference:")
        }.onFailure {
            Log.d("FirestoreService", "addUser failure, error: ${it.message}")
            throw it
        }
    }

    suspend fun addQuestion(question: Question) {
        runCatching {
            firestoreDB.collection(questionsPath).add(mapQuestionToMap(question)).await()
        }.onSuccess {
            Log.d("FirestoreService", "onSuccess, question added!")
        }.onFailure {
            Log.d("FirestoreService", "onFailure, error ${it.message}")
        }
    }

    suspend fun getQuestionsFromFirestore(): List<Question> {
        return runCatching {
            firestoreDB.collection(questionsPath)
                .get().await().toObjects(Question::class.java)
        }.fold(
            onSuccess = {
                it
            },
            onFailure = {
                throw it
            }
        )

    }

    private // Helper functions to convert between Question and Map
    fun mapQuestionToMap(question: Question): Map<String, Any> {
        return mapOf(
            "questionText" to question.questionText,
            "options" to question.options.map { option ->
                mapOf(
                    "optionText" to option.optionText,
                    "isCorrect" to option.isCorrect
                )
            },
            "userId" to question.userId,
        )
    }
}