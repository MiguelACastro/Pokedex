package com.example.pokeapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pokeappi.api.RegionRepository
import com.example.pokeappi.models.RegionNamedResult
import kotlin.collections.isNotEmpty

class RegionModel(
    private val repository: RegionRepository = RegionRepository() // Inyección del repositorio
) : ViewModel() {

    var regions by mutableStateOf<List<RegionNamedResult>>(emptyList())
        private set // Solo el ViewModel puede modificar el estado

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    suspend fun fetchRegions() {
        if (regions.isNotEmpty()) return

        isLoading = true
        errorMessage = null

        try {
            // El repositorio se encarga del trabajo pesado
            regions = repository.getAvailableRegions()
        } catch (e: Exception) {
            errorMessage = "No se pudieron cargar las regiones."
        } finally {
            isLoading = false
        }
    }
}