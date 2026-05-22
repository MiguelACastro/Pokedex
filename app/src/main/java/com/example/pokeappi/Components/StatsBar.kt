package com.example.pokeappi.Components

// Componente visual se encarga de mostrar las estadísticas de combate del Pokémon mediante barras de progreso horizontales

import android.R.color.black
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeappi.models.StatSlot

// Dibuja una fila con el nombre, valor y una barra de progreso para una estadística individual.
@Composable
fun StatsBar(
    label: String,
    value: Int,
    maxValue: Int = 150,
    color: Color
) {
    // Calcula el porcentaje de llenado
    val progress = (value.toFloat() / maxValue.toFloat()).coerceIn(0f, 1f)

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            color = Color.Black,
            modifier = Modifier.width(
                30.dp
            )
        )

        // Barra contenedora
        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
                .border(
                    border = BorderStroke(
                        2.dp,
                        color = Color.Black
                    )
                )
        ) {
            // Barra de progreso
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
        }

        Text(text = "$value", fontSize = 10.sp, color = Color.Black, modifier = Modifier.padding(start = 8.dp))
    }
}

