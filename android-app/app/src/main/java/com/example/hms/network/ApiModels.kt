package com.example.hms.network

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("roles") val roles: List<String>? = null
)

data class AuthResponse(
    @SerializedName("token") val token: String
)

