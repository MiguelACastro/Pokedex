package com.example.pokeappi.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokeappi.Components.ProfileTopBar
import com.example.pokeappi.R
import com.example.pokeappi.ui.theme.PokemonHollowFamily
import com.example.pokeappi.ui.theme.PokemonSolidFamily
import com.example.pokeappi.viewModel.PokemonViewModel
import com.example.pokeappi.viewModel.ProfileViewModel

// Definición de colores para las tarjetas
val BorderBlue = Color(0xFF1976D2)
val BorderPurple = Color(0xFF8E24AA)
val BorderRed = Color(0xFFD32F2F)
val BorderGreen = Color(0xFF388E3C)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToTeam: () -> Unit,
    onNavigateToRegions: () -> Unit,
    onLogoutSuccess: () -> Unit,
    profileViewModel: ProfileViewModel = viewModel(),
    pokemonViewModel: PokemonViewModel = viewModel()
) {
    val team by pokemonViewModel.pokemonTeam.collectAsState()

    Scaffold(
        topBar = { ProfileTopBar() },
        containerColor = Color.White
    ) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.pokemonfondo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                "Perfil de Entrenador",
                fontSize = 24.sp,
                fontFamily = PokemonHollowFamily,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // TARJETA ENTRENADOR
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, BorderBlue)

            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier
                            .size(60.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            profileViewModel.trainerName,
                            fontFamily = PokemonSolidFamily,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp)
                        Text(
                            "Nivel 25",
                            fontFamily = PokemonSolidFamily,
                            fontSize = 14.sp,
                            color = Color.DarkGray)
                    }
                }
            }

            // TARJETA EQUIPO
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToTeam() },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, BorderPurple)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Equipo ${team.size}/6",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = PokemonSolidFamily,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    val teamList = team.toList()
                    val chunkedTeam = teamList.chunked(3)

                    chunkedTeam.forEach { row ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            row.forEach { pokemonName ->
                                AsyncImage(
                                    model = "https://img.pokemondb.net/sprites/home/normal/$pokemonName.png",
                                    contentDescription = pokemonName,
                                    modifier = Modifier.size(60.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }
            }

            // TARJETA REGIONES
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToRegions() },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, BorderGreen)
            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Place, contentDescription = null, tint = BorderGreen, modifier = Modifier.size(32.dp))
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            "Regiones",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PokemonSolidFamily,
                            color = Color.Black
                            )
                        Text(
                            "Explora ${profileViewModel.regions.size} regiones: ${profileViewModel.regions.joinToString(", ")}",
                            fontFamily = PokemonSolidFamily,
                            color = Color.Black
                            )
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            // BOTÓN LOGOUT
            OutlinedButton(
                onClick = {
                    profileViewModel.logout()
                    onLogoutSuccess()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                border = BorderStroke(2.dp, BorderRed)
            ) {
                Icon(Icons.AutoMirrored.Rounded.ExitToApp, null, tint = BorderRed)
                Spacer(Modifier.width(8.dp))
                Text("Cerrar Sesión", color = BorderRed, fontWeight = FontWeight.Bold)
            }
        }
    }
}