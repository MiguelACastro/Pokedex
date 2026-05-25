package com.example.pokeappi.api

import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.models.PokemonListResponse
import com.example.pokeappi.models.PokemonSpecies
import com.example.pokeappi.models.RegionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): PokemonDetailResponse

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String): PokemonSpecies

    @GET("region")
    suspend fun getRegions(): RegionResponse
}