package com.example.hms.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hms.HmsApp

@Composable
fun authViewModel(): AuthViewModel {
    val app = androidx.compose.ui.platform.LocalContext.current.applicationContext as HmsApp
    return viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(app.container.authRepository) as T
        }
    })
}

