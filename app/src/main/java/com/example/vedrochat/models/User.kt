package com.example.vedrochat.models

import java.util.Date

data class User (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    companion object Factory {
        private var lastId = 0

        fun makeUser(fullName: String?): User {
            lastId++
            val nameParts: List<String>? = fullName?.split(" ")
            return User(
                id = "$lastId",
                firstName = nameParts?.getOrNull(0),
                lastName = nameParts?.getOrNull(1)
            )
        }
    }
}