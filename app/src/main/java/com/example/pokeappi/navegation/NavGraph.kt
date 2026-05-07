package com.example.pokeappi.navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokeappi.screens.DetailScreen
import com.example.pokeappi.screens.MainScreen
import com.example.pokeappi.viewModel.PokemonViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            MainScreen(viewModel = viewModel) { pokemonName ->
                navController.navigate("detail/$pokemonName")
            }
        }
        composable(
            "detail/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            DetailScreen(pokemonName = name, viewModel = viewModel) {
                navController.popBackStack()
            }
        }
    }
}
