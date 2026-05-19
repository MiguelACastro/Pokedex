package com.example.pokeappi.models

// Define el modelo de datos para la especie del Pokémon, permitiendo obtener textos descriptivos en diferentes idiomas y su categoría taxonómica desde la API.

import com.google.gson.annotations.SerializedName

// Informacion general de la especie el pokemon
data class PokemonSpecies(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    val genera: List<Genus>
)

// Entrada individual de texto descriptivo
data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: Language
)

// Categoria del pokemon
data class Genus(
    val genus: String,
    val language: Language
)

// Detalle del idioma
data class Language(
    val name: String
)