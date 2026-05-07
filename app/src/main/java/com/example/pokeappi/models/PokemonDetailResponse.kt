package com.example.pokeappi.models

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val stats: List<StatSlot>,
    val types: List<TypeSlot>,
    val abilities: List<AbilitySlot>
)

data class Sprites(
    val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)

data class StatSlot(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)

data class TypeSlot(
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class AbilitySlot(
    val ability: AbilityInfo
)

data class AbilityInfo(
    val name: String
)