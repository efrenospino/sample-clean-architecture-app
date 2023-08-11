package com.spotapp.mobile.data.sources.database.users

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    @Throws(SQLiteException::class)
    suspend fun save(user: UserDto)

    @Query("SELECT * FROM users")
    @Throws(SQLiteException::class)
    suspend fun findAll(): List<UserDto>

}
