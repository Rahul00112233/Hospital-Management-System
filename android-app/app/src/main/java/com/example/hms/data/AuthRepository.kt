package com.example.hms.data

import com.example.hms.network.AuthRequest
import com.example.hms.network.HmsApiService
import com.example.hms.network.RegisterRequest

class AuthRepository(
    private val api: HmsApiService,
    private val tokenStore: TokenDataStore
) {
    suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        val resp = api.login(AuthRequest(email, password))
        tokenStore.saveToken(resp.token)
    }

    suspend fun register(email: String, password: String, roles: List<String>? = null): Result<Unit> = runCatching {
        val resp = api.register(RegisterRequest(email, password, roles))
        tokenStore.saveToken(resp.token)
    }

    suspend fun logout() {
        tokenStore.clearToken()
    }

    suspend fun fetchProtectedSample(): Result<String> = runCatching {
        api.getPatientRecords()
    }

    suspend fun getToken(): String? = tokenStore.getToken()
}

