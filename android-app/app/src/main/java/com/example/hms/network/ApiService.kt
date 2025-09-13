package com.example.hms.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HmsApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body req: AuthRequest): AuthResponse

    @POST("/api/auth/register")
    suspend fun register(@Body req: RegisterRequest): AuthResponse

    @GET("/api/patient/records")
    suspend fun getPatientRecords(): String
}

