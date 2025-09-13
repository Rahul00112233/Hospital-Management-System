package com.example.hms.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequest(
    val email: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    val email: String,
    val password: String,
    val roles: List<String>? = null
)

@JsonClass(generateAdapter = true)
data class AuthResponse(
    val token: String
)

