package com.spotapp.mobile.data.sources.database.users

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserDto(
    @ColumnInfo("createdAt") var createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo("updatedAt") var updatedAt: Long = System.currentTimeMillis(),
    @Embedded var userInfo: UserInfoDto? = null,
) {
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class UserInfoDto(
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "name") var name: String,
)
