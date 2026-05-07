package com.example.pokeappi.models

data class PokemonListResponse(
    val results: List<SimplePokemon>
)

data class SimplePokemon(
    val name: String,
    val url: String
)