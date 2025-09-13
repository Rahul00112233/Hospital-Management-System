package com.example.hms.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hms.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val protectedData: String? = null
)

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = repo.login(email, password)
            _state.value = if (result.isSuccess) {
                onSuccess()
                _state.value.copy(isLoading = false)
            } else {
                _state.value.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Login failed")
            }
        }
    }

    fun register(email: String, password: String, onSuccess: () -> Unit) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = repo.register(email, password, null)
            _state.value = if (result.isSuccess) {
                onSuccess()
                _state.value.copy(isLoading = false)
            } else {
                _state.value.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Register failed")
            }
        }
    }

    fun fetchProtected() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = repo.fetchProtectedSample()
            _state.value = if (result.isSuccess) {
                _state.value.copy(isLoading = false, protectedData = result.getOrNull())
            } else {
                _state.value.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Fetch failed")
            }
        }
    }
}

