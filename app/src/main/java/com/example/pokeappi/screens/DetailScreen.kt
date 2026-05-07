package com.example.pokeappi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokeappi.Components.PokemonStatsList
import com.example.pokeappi.Components.StatBar
import com.example.pokeappi.models.StatSlot
import com.example.pokeappi.viewModel.PokemonViewModel

@Composable
fun DetailScreen(pokemonName: String, viewModel: PokemonViewModel, onBackClick: () -> Unit) {
    LaunchedEffect(pokemonName) {
        viewModel.getPokemonDetail(pokemonName)
    }

    val detail = viewModel.pokemonDetail.value

    Column(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onBackClick) { /* Icono de flecha atrás */ }

        if (detail != null) {
            AsyncImage(
                model = detail.sprites.other.officialArtwork.frontDefault,
                contentDescription = null,
                modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally)
            )

            PokemonStatsList(stats = detail.stats)
        } else {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}