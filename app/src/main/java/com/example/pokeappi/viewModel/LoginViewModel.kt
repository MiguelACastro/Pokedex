package com.example.pokeappi.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    // Estados para la UI
    var isLoading = mutableStateOf(false)
        private set
    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun loginUser(trainerName: String, pass: String, onSuccess: () -> Unit) {
        // Validación básica
        if (trainerName.isBlank() || pass.isBlank()) {
            errorMessage.value = "Por favor, ingresa tu nombre y contraseña"
            return
        }

        isLoading.value = true
        errorMessage.value = null

        // Reconstruye el correo ficticio con el que se registró el usuario
        val safeName = trainerName.trim().lowercase().replace("\\s+".toRegex(), "")
        val email = "$safeName@pokedex.com"

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                isLoading.value = false // Terminó la carga

                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    // Si falla, muestra un mensaje amigable
                    errorMessage.value = "Credenciales incorrectas o usuario no encontrado"
                }
            }
    }

    // Limpia el error cuando el usuario empieza a escribir de nuevo
    fun clearError() {
        errorMessage.value = null
    }
}