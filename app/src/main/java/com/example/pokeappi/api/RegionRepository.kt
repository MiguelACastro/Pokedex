package com.example.pokeappi.api

import com.example.pokeappi.models.RegionNamedResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegionRepository {
    private val apiService = RetrofitInstance.api


    suspend fun getAvailableRegions(): List<RegionNamedResult> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getRegions()
            response.results
        }
    }
}
