package com.example.pokeappi.models

// Respuesta de la API que contiene una lista simplificada de Pokémon.
data class PokemonListResponse(
    val results: List<SimplePokemon>
)

// Modelo básico para representar un Pokémon en la lista principal.
data class SimplePokemon(
    val name: String,
    val url: String,
    val type: String? = null
)
