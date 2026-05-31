package com.example.pokeappi.screens

// Pantalla principal: Gestiona la lista de Pokémon, el buscador en tiempo real y la hoja de detalles.

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeappi.ui.theme.PokemonHollowFamily
import com.example.pokeappi.Components.PokemonCard
import com.example.pokeappi.Components.PokemonDetails
import com.example.pokeappi.Components.PokemonTopBar
import com.example.pokeappi.viewModel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: PokemonViewModel = viewModel(),
    scaffoldPadding: PaddingValues
) {
    // Estados y variables del ViewModel
    val selectedDetail by viewModel.selectedPokemonDetail
    val showBottomSheet by viewModel.showDetailBottomSheet
    val sheetState = rememberModalBottomSheetState()
    val searchText = viewModel.searchText.value
    val speciesInfo by viewModel.speciesInfo
    val pokemonTeamState by viewModel.pokemonTeam.collectAsState()

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
        val filteredList = viewModel.filteredPokemon.value
        // Tarjeta cuando no hay resultados
        if (filteredList.isEmpty() && searchText.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(
                                    color = Color(0xFFFFEBEE),
                                    shape = RoundedCornerShape(40.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color(0xFFE57373),
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Sin Resultados",
                            fontFamily = PokemonHollowFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp,
                            color = Color(0xFFE57373),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "No encontramos ningún Pokémon que coincida con \"$searchText\".\nIntenta buscar con otro nombre o número.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        } else {
            // Cuadricula de Pokemon
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5))
            ) {
                // Muestra la lista filtrada del ViewModel
                items(filteredList) { pokemon ->
                    val isInTeam = pokemonTeamState.contains(pokemon.name)
                    PokemonCard(
                        pokemon = null,
                        name = pokemon.name,
                        url = pokemon.url,
                        type = pokemon.type,
                        isInTeam = isInTeam,
                        onClick = {
                            viewModel.selectPokemon(pokemon.name) // Abre el detalle
                        },
                        onTeamToggle = {
                            viewModel.toggleTeamMember(pokemon.name)
                        }
                    )
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
