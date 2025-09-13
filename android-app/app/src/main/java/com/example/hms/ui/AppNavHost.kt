package com.example.hms.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(
            onLoggedIn = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
            onNavigateRegister = { navController.navigate("register") }
        ) }
        composable("register") { RegisterScreen(onRegistered = { navController.navigate("home") { popUpTo("login") { inclusive = true } } }) }
        composable("home") { HomeScreen() }
    }
}

