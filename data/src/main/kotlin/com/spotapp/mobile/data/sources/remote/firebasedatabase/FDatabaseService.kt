package com.spotapp.mobile.data.sources.remote.firebasedatabase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.spotapp.mobile.data.sources.remote.firebasedatabase.model.UserData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FDatabaseService(private val auth: FirebaseAuth) {

    private val firebaseDB: DatabaseReference = Firebase.database.reference

    fun subscribeToRealtimeUpdates() {
        //TODO
    }

    fun setUserScore(scored: Boolean = true) {
        runCatching {
            auth.currentUser?.run {
                firebaseDB.child("users").child(uid)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                val userData = snapshot.getValue(UserData::class.java)
                                firebaseDB.child("users").child(uid).let {
                                    it.setValue(
                                        UserData(
                                            userId = userData?.userId,
                                            score = if (scored) (userData?.score?.plus(1)) else 0,
                                            lastConnection = getCurrentTimeFormatted()
                                        )
                                    )
                                }
                            } else {
                                firebaseDB.child("users").child(uid).let {
                                    it.setValue(
                                        UserData(
                                            userId = uid,
                                            score = if (scored) 1 else 0,
                                            lastConnection = getCurrentTimeFormatted()
                                        )
                                    )
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.d("FDatabaseService", "onCancelled")
                        }
                    })
            }
        }.onSuccess {
            Log.d("FDatabaseService", "onSuccess")
        }.onFailure {
            Log.d("FDatabaseService", "onFailure, error => ${it.message}")
        }

    }

    private fun getCurrentTimeFormatted(): String {
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

}