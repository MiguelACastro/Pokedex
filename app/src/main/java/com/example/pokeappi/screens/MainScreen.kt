package com.example.pokeappi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeappi.Components.PokemonCard
import com.example.pokeappi.models.SimplePokemon
import com.example.pokeappi.viewModel.PokemonViewModel

@Composable
fun MainScreen(
    viewModel: PokemonViewModel = viewModel(),
    onPokemonClick: (String) -> Unit
) {
    val lista by viewModel.pokemonList

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(lista) { pokemon ->
            PokemonCard(
                name = pokemon.name,
                url = pokemon.url,
                onClick = {

                    onPokemonClick(pokemon.name)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun MainScreenPreview() {
    val mockPokemon = listOf(
        SimplePokemon("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
        SimplePokemon("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
        SimplePokemon("squirtle", "https://pokeapi.co/api/v2/pokemon/7/")
    )

    Column {
        PokemonCard(name = "Pikachu", url = "https://pokeapi.co/api/v2/pokemon/25/") {}
    }
}