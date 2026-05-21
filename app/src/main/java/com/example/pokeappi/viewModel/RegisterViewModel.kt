package com.example.pokeappi.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    // Estados para la UI
    var isLoading = mutableStateOf(false)
        private set
    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun registerUser(trainerName: String, pass: String, onSuccess: () -> Unit) {
        // Validación básica
        if (trainerName.isBlank() || pass.length < 6) {
            errorMessage.value = "Nombre requerido y contraseña de mínimo 6 caracteres"
            return
        }

        isLoading.value = true
        errorMessage.value = null

        // Crea un correo ficticio seguro (sin espacios ni mayúsculas)
        val safeName = trainerName.trim().lowercase().replace("\\s+".toRegex(), "")
        val email = "$safeName@pokedex.com"

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid ?: return@addOnCompleteListener

                    // Guarda el nombre en el perfil rápido de Firebase Auth
                    val profileUpdates = userProfileChangeRequest {
                        displayName = trainerName
                    }

                    user.updateProfile(profileUpdates).addOnCompleteListener {
                        // Guarda también en Firestore
                        val userMap = hashMapOf(
                            "trainerName" to trainerName,
                            "createdAt" to System.currentTimeMillis()
                        )

                        db.collection("users").document(userId).set(userMap)
                            .addOnSuccessListener {
                                isLoading.value = false
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                isLoading.value = false
                                errorMessage.value = "Error al guardar datos: ${e.localizedMessage}"
                            }
                    }
                } else {
                    isLoading.value = false
                    // Muestra el error de Firebase (ej. usuario ya existe)
                    errorMessage.value = task.exception?.localizedMessage ?: "Error desconocido"
                }
            }
    }

    // Función auxiliar para limpiar el error si el usuario empieza a escribir de nuevo
    fun clearError() {
        errorMessage.value = null
    }
}