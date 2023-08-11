package com.spotapp.mobile.data.sources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spotapp.mobile.data.sources.database.users.UserDao
import com.spotapp.mobile.data.sources.database.users.UserDto

@Database(entities = [UserDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun get(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java, "spotapp.db"
        ).build()
    }
}
