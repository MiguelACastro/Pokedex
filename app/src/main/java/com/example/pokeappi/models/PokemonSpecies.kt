package com.example.pokeappi.models

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    val genera: List<Genus>
)

data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String,
    val language: Language
)

data class Genus(
    val genus: String,
    val language: Language
)

data class Language(
    val name: String
)