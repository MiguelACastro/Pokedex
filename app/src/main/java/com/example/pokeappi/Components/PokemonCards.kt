package com.example.pokeappi.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.ui.theme.PokemonHollowFamily
import com.example.pokeappi.ui.theme.getPokemonColor
import com.example.pokeappi.R

@Composable
fun PokemonCard(
    pokemon: PokemonDetailResponse?,
    name: String,
    url: String,
    type: String?,
    isInTeam: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onTeamToggle: () -> Unit,
) {
    val primaryType = type ?: pokemon?.types?.firstOrNull()?.type?.name
    val borderColor = getPokemonColor(primaryType)

    val id = url.split("/").filter { it.isNotEmpty() }.last()

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, borderColor),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = name.replaceFirstChar { it.uppercase() },
                    fontFamily = PokemonHollowFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Text(
                    text = "No. ${id.padStart(3, '0')}",
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
        IconButton(
            onClick = onTeamToggle,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(32.dp)
        ) {
            val pokeballImage = if (isInTeam) {
                painterResource(id = R.drawable.pokeballred)
            } else {
                painterResource(id = R.drawable.pokeball)
            }

            Image(
                painter = pokeballImage,
                contentDescription = if (isInTeam) "Quitar del equipo" else "Agregar al equipo",
                modifier = Modifier
                    .size(32.dp)
            )
        }
    }
}

