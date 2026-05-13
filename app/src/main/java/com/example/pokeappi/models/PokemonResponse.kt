package com.example.pokeappi.models

// Define la estructura de datos básica para recibir la lista inicial de la API, capturando únicamente el nombre y la dirección web de cada Pokémon para su visualización previa.

// Respuesta de la API que contiene una lista simplificada de Pokémon.
data class PokemonListResponse(
    val results: List<SimplePokemon>
)

// Modelo básico para representar un Pokémon en la lista principal.
data class SimplePokemon(
    val name: String,
    val url: String
)