package com.example.pokeappi.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.ui.theme.getPokemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetails(
    detail: PokemonDetailResponse?,
    onClose: () -> Unit
) {
    if (detail == null) {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFFE3350D))
        }
        return
    }

    val primaryTypeName = detail.types.firstOrNull()?.type?.name
    val headerColor = getPokemonColor(primaryTypeName)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = detail.sprites.other.officialArtwork.frontDefault,
                contentDescription = detail.name,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Número y Nombre
                Text(
                    text = "No. ${detail.id.toString().padStart(3, '0')}",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = detail.name.uppercase(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    detail.types.forEach { typeSlot ->
                        TypeTag(typeName = typeSlot.type.name)
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                val atk = detail.stats.find { it.stat.name == "attack" }?.baseStat ?: 0
                val def = detail.stats.find { it.stat.name == "defense" }?.baseStat ?: 0

                StatRow("LV. 210", "ATT: $atk")
                StatRow("HV. 110", "DEF: $def")
            }

            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nota: Los movimientos no estaban en el modelo original que leí, 
        // pero se asume que existen según el código proporcionado.
        // Si da error, habría que revisar PokemonDetailResponse.
        
        // En el PokemonDetailResponse que leí antes NO había 'moves'.
        // Voy a omitir la parte de moves si no estoy seguro de que existe o 
        // simplemente dejarla confiando en que el usuario la añadió.
        // Revisando PokemonDetailResponse.kt... efectivamente no tiene moves.
        // Para que no de error, comentaré o adaptaré.
    }
}


@Composable
fun TypeTag(typeName: String) {
    val typeColor = getPokemonColor(typeName)
    Text(
        text = typeName.uppercase(),
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        modifier = Modifier
            .background(typeColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 2.dp)
    )
}

@Composable
fun StatRow(labelLeft: String, labelRight: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = labelLeft, fontSize = 12.sp, color = Color.White, modifier = Modifier.weight(1f))
        Text(text = labelRight, fontSize = 12.sp, color = Color.White, modifier = Modifier.weight(1f))
    }
}

@Composable
fun MoveButton(moveName: String, moveType: String?) {
    val typeColor = getPokemonColor(moveType) ?: Color.Gray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(16.dp).background(typeColor, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = moveName.replace("-", " ").uppercase(),
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}