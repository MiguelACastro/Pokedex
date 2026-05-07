package com.example.pokeappi.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.pokeappi.api.RetrofitInstance
import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.models.SimplePokemon

class PokemonViewModel : ViewModel() {
    var pokemonList = mutableStateOf<List<SimplePokemon>>(emptyList())

    var pokemonDetail = mutableStateOf<PokemonDetailResponse?>(null)

    init {
        fetchList()
    }

    private fun fetchList() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getPokemonList(151)
            pokemonList.value = response.results
        }
    }

    fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            pokemonDetail.value = null
            val response = RetrofitInstance.api.getPokemonDetails(name)
            pokemonDetail.value = response
        }
    }
}