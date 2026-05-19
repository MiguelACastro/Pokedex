package com.example.pokeappi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraph
import com.example.pokeappi.navegation.NavGraph
import com.example.pokeappi.ui.theme.PokeAppiTheme // Asegúrate que el nombre de tu tema sea este

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