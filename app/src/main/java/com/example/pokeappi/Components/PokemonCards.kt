package com.example.pokeappi.Components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokeappi.ui.theme.getPokemonColor

@Composable
fun PokemonCard(name: String, url: String, type: String? ="dragon", onClick: () -> Unit) {
    val cardColor = Color(0xFFE3350D)

    val id = url.split("/").filter { it.isNotEmpty() }.last()
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = name.replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )

            Text(text = "No. 00${id.takeLast(1)}", fontSize = 10.sp, color = Color.Gray)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Lv. 25", fontSize = 10.sp, color = Color.Gray)
                Text(text = "HP: 110", fontSize = 10.sp, color = Color.Gray)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "ATT: 55", fontSize = 10.sp, color = Color.Gray)
                Text(text = "DEF: 40", fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}