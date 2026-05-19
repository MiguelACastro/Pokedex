package com.example.pokeappi.ui.theme

import androidx.compose.ui.graphics.Color

val TypeGrass = Color(0xFF7AC74C)
val TypeFire = Color(0xFFEE8130)
val TypeWater = Color(0xFF6390F0)
val TypeBug = Color(0xFFA6B91A)
val TypeNormal = Color(0xFFA8A77A)
val TypePoison = Color(0xFFA33EA1)
val TypeElectric = Color(0xFFF7D02C)
val TypeGround = Color(0xFFE2BF65)
val TypeFairy = Color(0xFFD685AD)
val TypeFighting = Color(0xFFC22E28)
val TypePsychic = Color(0xFFF95587)
val TypeRock = Color(0xFFB6A136)
val TypeGhost = Color(0xFF735797)
val TypeIce = Color(0xFF96D9D6)
val TypeDragon = Color(0xFF6F35FC)
val TypeSteel = Color(0xFFB7B7CE)
val TypeFlying = Color(0xFFA98FF0)
val TypeDark = Color(0xFF705746)

fun getPokemonColor(type: String?): Color {
    return when (type?.lowercase()) {
        "grass" -> TypeGrass
        "fire" -> TypeFire
        "water" -> TypeWater
        "bug" -> TypeBug
        "normal" -> TypeNormal
        "poison" -> TypePoison
        "electric" -> TypeElectric
        "ground" -> TypeGround
        "fairy" -> TypeFairy
        "fighting" -> TypeFighting
        "psychic" -> TypePsychic
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "ice" -> TypeIce
        "dragon" -> TypeDragon
        "steel" -> TypeSteel
        "flying" -> TypeFlying
        "dark" -> TypeDark
        else -> Color.Gray
    }
}