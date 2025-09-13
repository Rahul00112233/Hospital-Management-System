package com.example.hms

import android.app.Application
import com.example.hms.di.AppContainer

class HmsApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}

