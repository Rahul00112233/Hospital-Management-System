package com.example.hms.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(onRegistered: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val vm = authViewModel()
    val state = vm.state.collectAsState()
    isLoading = state.value.isLoading
    error = state.value.error
    val availableRoles = listOf("PATIENT", "DOCTOR", "NURSE", "RECEPTIONIST", "ADMIN")
    var selectedRoles by remember { mutableStateOf(setOf("PATIENT")) }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "Register", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())

        if (error != null) {
            Spacer(Modifier.height(8.dp))
            Text(text = error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))
        Text("Select roles")
        Spacer(Modifier.height(8.dp))
        availableRoles.forEach { role ->
            val checked = selectedRoles.contains(role)
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = checked, onCheckedChange = {
                    selectedRoles = if (it) selectedRoles + role else selectedRoles - role
                })
                Spacer(Modifier.width(8.dp))
                Text(role)
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = { vm.registerWithRoles(email, password, selectedRoles.toList(), onRegistered) }, enabled = !isLoading, modifier = Modifier.fillMaxWidth()) {
            Text("Register")
        }
    }
}

