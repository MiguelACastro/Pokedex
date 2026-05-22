package com.example.pokeappi.Components

//organizar y mostrar la información específica de un Pokémon seleccionado dentro de una hoja inferior.

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.models.StatSlot
import com.example.pokeappi.ui.theme.PokemonHollowFamily
import com.example.pokeappi.ui.theme.PokemonSolidFamily
import com.example.pokeappi.ui.theme.getPokemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetails(
    detail: PokemonDetailResponse?,
    description: String,
    types: List<String>,
    onClose: () -> Unit
) {
    // Si no hay datos, mostrar cargando
    if (detail == null) {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFFE3350D))
        }
        return
    }

    // Color principal segun el tipo del pokemon
    val primaryTypeName = detail.types.firstOrNull()?.type?.name
    val headerColor = getPokemonColor(primaryTypeName)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Encabezado con imagen y datos basicos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen oficial del pokemon
            AsyncImage(
                model = detail.sprites.other.officialArtwork.frontDefault,
                contentDescription = detail.name,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Numero y Nombre del pokemon
                Text(
                    text = "No. ${detail.id.toString().padStart(3, '0')}",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = detail.name.uppercase(),
                    fontSize = 18.sp,
                    fontFamily = PokemonHollowFamily,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Etiquetas de tipos
                Row {
                    detail.types.forEach { typeSlot ->
                        TypeTag(typeName = typeSlot.type.name)
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Stats rapidos: Ataque y Defensa
                val atk = getStatValue(detail.stats, "attack")
                val def = getStatValue(detail.stats, "defense")
                val hp = getStatValue(detail.stats, "hp")
                val spd = getStatValue(detail.stats, "speed")

                Column(modifier = Modifier.padding(top = 8.dp)) {
                    StatsBar("HP", hp, 255, Color(0xFF4CAF50))
                    StatsBar("ATK", atk, 255, Color(0xFFF44336))
                    StatsBar("DEF", def, 255, Color(0xFF2196F3))
                    StatsBar("SPD", spd, 255, Color(0xFFE91E63))
                }
            }

            // Boton para cerrar la ventana
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Seccion de descripcion en español
        Text(
            text = "DESCRIPCIÓN",
            fontFamily = PokemonSolidFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description.replace("\n", " "), // Limpia saltos de linea
            fontSize = 14.sp,
            fontFamily = PokemonSolidFamily,
            color = Color.Black,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Componente para las etiquetas de tipo (Fuego, Agua, etc)
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

fun getStatValue(stats: List<StatSlot>, statName: String): Int {
    return stats.find { it.stat.name == statName }?.baseStat ?: 0
}

