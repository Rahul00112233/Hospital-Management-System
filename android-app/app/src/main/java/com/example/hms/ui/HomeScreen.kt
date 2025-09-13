package com.example.hms.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    val vm = authViewModel()
    val state = vm.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text(text = "Welcome to HMS", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        Button(onClick = { vm.fetchProtected() }) { Text("Fetch protected data") }
        Spacer(Modifier.height(12.dp))
        if (state.value.protectedData != null) {
            Text("Protected: ${state.value.protectedData}")
        }
        if (state.value.error != null) {
            Text(text = state.value.error!!, color = MaterialTheme.colorScheme.error)
        }
    }
}

