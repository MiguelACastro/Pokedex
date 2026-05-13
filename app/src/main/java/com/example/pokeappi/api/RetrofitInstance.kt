package com.example.pokeappi.api

//conexión con el servidor externo, definiendo cómo se construyen las peticiones y cómo se transforman los datos JSON de la PokéAPI en objetos de Kotlin.

import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.models.PokemonSpecies
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}
