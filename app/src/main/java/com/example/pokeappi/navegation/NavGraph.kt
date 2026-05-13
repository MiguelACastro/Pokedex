package com.example.pokeappi.navegation

// Importaciones para navegación y gestión de estados
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeappi.screens.MainScreen
import com.example.pokeappi.viewModel.PokemonViewModel

@Composable
fun NavGraph() {
    // Controlador de rutas
    val navController = rememberNavController()

    // ViewModel compartido para las pantallas
    val viewModel: PokemonViewModel = viewModel()

    // Configuracion del host de navegacion
    NavHost(navController = navController, startDestination = "list") {

        // Ruta para la pantalla principal de la PokeDex
        composable("list") {
            MainScreen(viewModel = viewModel)
        }
    }
}