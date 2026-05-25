package com.example.pokeappi.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import com.example.pokeappi.ui.theme.PokemonFontFamily
import com.example.pokeappi.ui.theme.PokemonHollowFamily
import com.example.pokeappi.ui.theme.PokemonSolidFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    searchText: String,
    onSearchValueChange: (String) -> Unit,

){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 52.dp, bottom = 36.dp),
        contentAlignment = Alignment.Center
    ){
    Column( horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = "Mi POKÉDEX",
                fontSize = 22.sp,
                fontFamily = PokemonSolidFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.White
            )
        }

        // Caja de texto para buscar
        TextField(
            value = searchText,
            onValueChange = onSearchValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(50.dp),
            placeholder = { Text("Buscar Pokémon o Número...", fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
        }
}

@Composable
fun LoginTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 52.dp, bottom = 36.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MI POKÉDEX",
                fontSize = 30.sp,
                fontFamily = PokemonSolidFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "¡BIENVENIDO, ENTRENADOR!",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun RegisterTopBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 52.dp, bottom = 36.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MI POKÉDEX",
                fontSize = 35.sp,
                fontFamily = PokemonSolidFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "¡ÚNETE A LA AVENTURA!",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
        }
    }
}
@Composable
fun RegionTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 52.dp, bottom = 36.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MI POKÉDEX",
                fontSize = 30.sp,
                fontFamily = PokemonSolidFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "REGIONES",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
        }
    }
}