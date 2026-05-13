package com.example.pokeappi.navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeappi.screens.MainScreen
import com.example.pokeappi.viewModel.PokemonViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            MainScreen(viewModel = viewModel)
        }
    }
}