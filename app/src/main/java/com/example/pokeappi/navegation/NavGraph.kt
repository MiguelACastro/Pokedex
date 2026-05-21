package com.example.pokeappi.navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeappi.screens.LoginView
import com.example.pokeappi.screens.MainScreen
import com.example.pokeappi.screens.RegisterView
import com.example.pokeappi.viewModel.PokemonViewModel

@Composable
fun NavGraph() {
    // Controlador de rutas
    val navController = rememberNavController()

    // ViewModel compartido para las pantallas
    val viewModel: PokemonViewModel = viewModel()

    // Configuracion del host de navegacion
    NavHost(navController = navController, startDestination = "login") {

        //Vista Login
        composable("login") {
            LoginView(
                onLoginSuccess = {
                    // Ruta para la pantalla principal de la PokeDex
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onCreateAccountClick = {
                    // Redirecciona al registro
                    navController.navigate("register")
                })
        }

        //Vista Registro
        composable("register") {
            RegisterView(
                onRegisterSuccess = {
                    // Si se registra con éxito, vamos directo a "login"
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onLoginClick = {
                    // Si ya tiene cuenta, regresamos a la pantalla anterior (login)
                    navController.popBackStack()
                }
            )
        }

        // Vista Principal
        composable("main") {
            MainScreen()
        }
    }
}