package com.example.pokeappi.Components

// Tarjeta individual para mostrar un Pokémon en la cuadrícula principal.

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PokemonCard(name: String, url: String, type: String? ="dragon", onClick: () -> Unit) {
    // Color de acento para el borde de la tarjeta
    val cardColor = Color(0xFFE3350D)

    // Extrae el ID numérico desde la URL de la PokeAPI
    val id = url.split("/").filter { it.isNotEmpty() }.last()

    // Construye la URL de la imagen usando el arte oficial de GitHub
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }, // Detecta el toque para abrir detalles
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, cardColor.copy(alpha = 0.2f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Carga asíncrona de la imagen del Pokémon
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre del Pokémon con la primera letra en mayúscula
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )

            // Formato visual del número de la PokéDex
            Text(text = "No. ${id.padStart(3, '0')}", fontSize = 10.sp, color = Color.Gray)

            // Fila de estadísticas: Nivel y Vida (HP)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Lv. 25", fontSize = 10.sp, color = Color.Gray)
                Text(text = "HP: 110", fontSize = 10.sp, color = Color.Gray)
            }

            // Fila de estadísticas: Ataque y Defensa
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "ATT: 55", fontSize = 10.sp, color = Color.Gray)
                Text(text = "DEF: 40", fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}