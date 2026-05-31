package com.example.pokeappi.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeappi.Components.PokemonCard
import com.example.pokeappi.Components.PokemonDetails
import com.example.pokeappi.Components.PokemonTopBar
import com.example.pokeappi.ui.theme.PokemonHollowFamily
import com.example.pokeappi.viewModel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    viewModel: PokemonViewModel,
    onPokemonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Nombres de los integrantes en Firebase (Set<String>)
    val teamByNames by viewModel.pokemonTeam.collectAsState()

    // Lista completa de Pokémon de tu API (es un MutableState, no un Flow)
    val allPokemons = viewModel.filteredPokemon.value

    // Filtra la lista global conservando solo los que están en el equipo
    val teamList = allPokemons.filter { teamByNames.contains(it.name) }
    val selectedDetail by viewModel.selectedPokemonDetail
    val showBottomSheet by viewModel.showDetailBottomSheet
    val sheetState = rememberModalBottomSheetState()
    val speciesInfo by viewModel.speciesInfo
    val searchText = viewModel.searchText.value

    Scaffold(
        topBar = {
            PokemonTopBar(
                searchText = searchText,
                onSearchValueChange = { newText ->
                    viewModel.onSearchTextChange(newText)
                }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
    Column(
        modifier = modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
        Text(
            text = "Mi Equipo Pokémon (${teamList.size}/6)",
            fontFamily = PokemonHollowFamily,
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
// Mensaje cuando el equipo esta vacio o no coincide con la busqueda
        if (teamList.isEmpty()) {
            if (searchText.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(
                                        color = Color(0xFFFFEBEE),
                                        shape = RoundedCornerShape(30.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = Color(0xFFE57373),
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Sin Coincidencias",
                                fontFamily = PokemonHollowFamily,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                color = Color(0xFFE57373),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Ningún Pokémon en tu equipo coincide con \"$searchText\".",
                                fontSize = 13.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tu equipo está vacío. ¡Ve a la pantalla principal y selecciona hasta 6 Pokémon!",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(teamList) { pokemon ->
                    PokemonCard(
                        pokemon = null,
                        name = pokemon.name,
                        url = pokemon.url,
                        type = pokemon.type,
                        isInTeam = true,
                        onClick = { onPokemonClick(pokemon.name) },
                        onTeamToggle = { viewModel.toggleTeamMember(pokemon.name) }
                    )
                }
            }
        }
        }
    }
// Ventana emergente de detalles (BottomSheet)
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.closeDetail() },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {
            val descriptionEs = speciesInfo?.flavorTextEntries?.find { it.language.name == "es" }?.flavorText ?: "Sin descripción"
            val types = selectedDetail?.types?.map { it.type.name} ?: emptyList()

            // Contenido del detalle del Pokemon
            PokemonDetails(
                detail = selectedDetail,
                description = descriptionEs,
                types = types,
                onClose = { viewModel.closeDetail() }
            )
        }
    }
}
