package com.example.pokeappi.models

data class RegionResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RegionNamedResult>
)

data class RegionNamedResult(
    val name: String,
    val url: String
)
