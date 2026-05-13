package com.example.pokeappi.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeappi.api.RetrofitInstance
import com.example.pokeappi.models.PokemonDetailResponse
import com.example.pokeappi.models.PokemonSpecies
import com.example.pokeappi.models.SimplePokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    var pokemonList = mutableStateOf<List<SimplePokemon>>(emptyList())

    private val _selectedPokemonDetail = mutableStateOf<PokemonDetailResponse?>(null)
    val selectedPokemonDetail: State<PokemonDetailResponse?> = _selectedPokemonDetail

    private val _showDetailBottomSheet = mutableStateOf(false)
    val showDetailBottomSheet: State<Boolean> = _showDetailBottomSheet

    private val _speciesInfo = mutableStateOf<PokemonSpecies?>(null)
    val speciesInfo: State<PokemonSpecies?> = _speciesInfo

    init {
        fetchList()
    }

    private fun fetchList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonList(151)
                pokemonList.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun closeDetail() {
        _showDetailBottomSheet.value = false
        _selectedPokemonDetail.value = null
    }
    fun selectPokemon(name: String) {
        viewModelScope.launch {
            _showDetailBottomSheet.value = true
            _selectedPokemonDetail.value = null
            _speciesInfo.value = null

            try {
                val detailDeferred = async { RetrofitInstance.api.getPokemonDetails(name) }
                val speciesDeferred = async { RetrofitInstance.api.getPokemonSpecies(name) }

                _selectedPokemonDetail.value = detailDeferred.await()
                _speciesInfo.value = speciesDeferred.await()

            } catch (e: Exception) {
                _showDetailBottomSheet.value = false
                e.printStackTrace()
            }
        }
    }
}