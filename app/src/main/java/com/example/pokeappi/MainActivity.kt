package com.example.pokeappi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraph
import com.example.pokeappi.navegation.NavGraph
import com.example.pokeappi.ui.theme.PokeAppiTheme // Asegúrate que el nombre de tu tema sea este


/**
 * MainActivity
 * * Punto de entrada principal de la aplicación Pokedex.
 * Inicializa el ciclo de vida de la actividad y configura la interfaz de usuario
 * utilizando Jetpack Compose, aplicando el tema global [PokeAppiTheme] y
 * montando el grafo de navegación principal [NavGraph].
 * * Arquitectura del proyecto:
 * - data: Manejo de la PokeAPI, repositorios y modelos de datos de los pokémones.
 * - navegation: Control de rutas y pantallas de la aplicación.
 * - screens: Vistas modulares de la interfaz de usuario en Compose.
 * - ui.theme: Configuración de estilos, colores y tipografía de la app.
 * * Contribución al proyecto: Documentación base de arquitectura y setup inicial.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppiTheme {
                NavGraph()
            }
        }
    }
}