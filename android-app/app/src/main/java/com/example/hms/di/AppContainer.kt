package com.example.hms.di

import android.content.Context
import com.example.hms.BuildConfig
import com.example.hms.data.AuthRepository
import com.example.hms.data.TokenDataStore
import com.example.hms.network.AuthInterceptor
import com.example.hms.network.HmsApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
// using Gson + Scalars converters

class AppContainer(context: Context) {
    private val tokenDataStore = TokenDataStore(context)

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val authInterceptor = AuthInterceptor { tokenDataStore.getToken() }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()

    private val gson: Gson = GsonBuilder().setLenient().create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(retrofit2.converter.scalars.ScalarsConverterFactory.create())
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(gson))
        .build()

    val api: HmsApiService = retrofit.create(HmsApiService::class.java)

    val authRepository = AuthRepository(api, tokenDataStore)
}

