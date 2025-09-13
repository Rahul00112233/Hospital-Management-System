package com.example.hms.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: suspend () -> String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = try {
            // Best-effort: token retrieval might be from DataStore (suspend). Block briefly.
            kotlinx.coroutines.runBlocking { tokenProvider() }
        } catch (e: Exception) {
            null
        }

        if (token.isNullOrBlank()) {
            return chain.proceed(original)
        }
        val authed = original.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(authed)
    }
}

