package com.example.pokeappi.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {

    var trainerName by mutableStateOf("Cargando...")
        private set

    var regions by mutableStateOf(listOf("Kanto", "Johto", "Hoenn"))
        private set

    var team by mutableStateOf(listOf<Int>())
        private set

    val maxTeamSize = 6

    init {
        fetchUserData()
        fetchUserTeam()
    }

    private fun fetchUserData() {
        val user = FirebaseAuth.getInstance().currentUser
        trainerName = user?.displayName ?: user?.email?.substringBefore("@") ?: "Entrenador"
    }

    private fun fetchUserTeam() {
        // Aquí llamas a tu función que trae la lista de tu base de datos o API
        // team = ...
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}