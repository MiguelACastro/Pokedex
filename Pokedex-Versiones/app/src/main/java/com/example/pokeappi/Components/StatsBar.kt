package com.example.pokeappi.Components

// Componente visual se encarga de mostrar las estadísticas de combate del Pokémon mediante barras de progreso horizontales

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeappi.models.StatSlot


// Dibuja una fila con el nombre, valor y una barra de progreso para una estadística individual.
@Composable
fun StatBar(
    statName: String,
    statValue: Int,
    maxStat: Int = 255,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nombre del atributo (ej. HP, ATTACK)
        Text(
            text = statName,
            modifier = Modifier.weight(0.2f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        // Valor numérico actual
        Text(
            text = statValue.toString(),
            modifier = Modifier.weight(0.15f),
            fontSize = 12.sp
        )

        // Barra visual proporcional al valor máximo
        LinearProgressIndicator(
            progress = statValue.toFloat() / maxStat.toFloat(),
            modifier = Modifier
                .weight(0.65f)
                .height(8.dp),
            color = color,
            trackColor = Color.LightGray.copy(alpha = 0.3f),
            strokeCap = StrokeCap.Round
        )
    }
}

@Composable
fun PokemonStatsList(stats: List<StatSlot>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Estadísticas Base",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp),
            color = Color(0xFF303030)
        )

        // Itera sobre cada estadística para crear su barra correspondiente
        stats.forEach { statSlot ->
            // Selección de color según el tipo de estadística para mejorar la UI
            val color = when (statSlot.stat.name.lowercase()) {
                "hp" -> Color(0xFF4CAF50)
                "attack" -> Color(0xFFF44336)
                "defense" -> Color(0xFF2196F3)
                "special-attack" -> Color(0xFFFF9800)
                "special-defense" -> Color(0xFF9C27B0)
                "speed" -> Color(0xFFFFEB3B)
                else -> Color.Gray
            }

            StatBar(
                statName = statSlot.stat.name.uppercase(),
                statValue = statSlot.baseStat,
                color = color
            )
        }
    }
}