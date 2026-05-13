package com.example.pokeappi.viewModel

//gestionar los datos de los Pokémon y controlar qué se muestra en la pantalla.

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

    // Lista para el estado de la UI
    var pokemonList = mutableStateOf<List<SimplePokemon>>(emptyList())

    // Lista de apoyo para guardar los originales y no perderlos al filtrar
    private var allPokemon = listOf<SimplePokemon>()

    // Lista que se actualiza con el filtro de busqueda
    var filteredPokemon = mutableStateOf<List<SimplePokemon>>(emptyList())

    // Estados para los detalles del pokemon
    private val _selectedPokemonDetail = mutableStateOf<PokemonDetailResponse?>(null)
    val selectedPokemonDetail: State<PokemonDetailResponse?> = _selectedPokemonDetail

    // Estado para mostrar o cerrar el BottomSheet
    private val _showDetailBottomSheet = mutableStateOf(false)
    val showDetailBottomSheet: State<Boolean> = _showDetailBottomSheet

    // Estado para la informacion de la especie
    private val _speciesInfo = mutableStateOf<PokemonSpecies?>(null)
    val speciesInfo: State<PokemonSpecies?> = _speciesInfo

    init {
        fetchList()
    }

    // Funcion para obtener la lista de pokemon de la API
    private fun fetchList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonList(151)
                // Guardamos los datos en la lista maestra y en las listas de visualizacion
                allPokemon = response.results
                pokemonList.value = response.results
                filteredPokemon.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Funcion para cerrar el detalle del pokemon
    fun closeDetail() {
        _showDetailBottomSheet.value = false
        _selectedPokemonDetail.value = null
    }

    // Funcion para seleccionar un pokemon y cargar sus datos
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

    // Funcion para el filtro de busqueda por nombre o numero
    fun onSearchTextChange(query: String) {
        filteredPokemon.value = if (query.isEmpty()) {
            allPokemon
        } else {
            allPokemon.filter { pokemon ->
                // Obtenemos el ID desde la URL del modelo
                val id = pokemon.url.split("/").filter { it.isNotEmpty() }.last()

                // Filtramos si empieza con la letra o si es el numero exacto
                pokemon.name.startsWith(query, ignoreCase = true) || id == query
            }
        }
    }
}