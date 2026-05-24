package com.example.pokeappi.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeappi.ui.theme.getPokemonColor

sealed class NavigationItem(val route: String, val icon: ImageVector, val label: String) {
    object Pokedex : NavigationItem("main", Icons.Default.List, "POKÉDEX")
    object Equipo : NavigationItem("equipo", Icons.Default.FavoriteBorder, "EQUIPO")
    object Regiones : NavigationItem("regiones", Icons.Default.Place, "REGIONES")
    object Perfil : NavigationItem("perfil", Icons.Default.Person, "PERFIL")
}

@Composable
fun PokeBottomBar(
    currentRoute: String?,
    onNavItemClick: (String) -> Unit
) {
    val items = listOf(
        NavigationItem.Pokedex,
        NavigationItem.Equipo,
        NavigationItem.Regiones,
        NavigationItem.Perfil
    )

    BottomAppBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomBarItem(
                    icon = item.icon,
                    label = item.label,
                    isSelected = currentRoute == item.route,
                    onClick = { onNavItemClick(item.route) }
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val pokeRed = getPokemonColor("fighting")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) pokeRed else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) pokeRed else Color.Gray,
            letterSpacing = 0.5.sp
        )
    }
}