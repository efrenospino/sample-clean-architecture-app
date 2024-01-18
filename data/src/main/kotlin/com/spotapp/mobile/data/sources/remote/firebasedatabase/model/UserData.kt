package com.spotapp.mobile.data.sources.remote.firebasedatabase.model

data class UserData(
    val userId: String?,
    val score: Int?,
    val lastConnection: String?,
) {
    //Note: this is needed to read the data from the firebase database
    //firebase database throws this exception: UserData does not define a no-argument constructor
    constructor() : this(null, null, null)
}
