package com.example.pokeappi.screens

// Pantalla de inicio de sesión.
// Diseño: header rojo redondeado, campos estilizados con íconos,
// link de recuperación, botón principal y barra de navegación inferior.

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeappi.viewModel.LoginViewModel

// ─────────────────────────────────────────
// Paleta de colores (consistente con MainScreen)
// ─────────────────────────────────────────
private val PokeRed        = Color(0xFFE3350D)
private val PokeBackground = Color(0xFFD9D9D9)
private val FieldBg        = Color(0xFFEAEAEA)
private val TextPrimary    = Color(0xFF1A1A1A)
private val TextSecondary  = Color(0xFF666666)
private val IconTint       = Color(0xFF444444)

// ─────────────────────────────────────────
// Pantalla de Login
// ─────────────────────────────────────────
@Composable
fun LoginView(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onLoginClick: (username: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    onNavItemClick: (String) -> Unit = {}
) {
    var trainerName by remember { mutableStateOf("") }
    var password    by remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    Scaffold(
        bottomBar = {
            LoginBottomBar(onNavItemClick = onNavItemClick)
        },
        containerColor = PokeBackground
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ── Header rojo con esquinas inferiores redondeadas ──
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                    .background(PokeRed)
                    .padding(top = 52.dp, bottom = 36.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "MI POKÉDEX",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¡BIENVENIDO, ENTRENADOR!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                }
            }

            // ── Cuerpo del formulario ──
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(36.dp))

                // Título de sección
                Text(
                    text = "INICIO DE SESIÓN",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ── Campo: Nombre de Entrenador ──
                LoginTextField(
                    value = trainerName,
                    onValueChange = {
                        trainerName = it
                        viewModel.clearError()
                    },
                    placeholder = "Nombre de Entrenador",
                    leadingIcon = Icons.Default.Person,
                    keyboardType = KeyboardType.Text,
                    isPassword = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ── Campo: Contraseña ──
                LoginTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        viewModel.clearError()
                    },
                    placeholder = "Contraseña",
                    leadingIcon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(14.dp))

                // ── Link: ¿Olvidaste tu código? ──
                Text(
                    text = "¿Olvidaste tu código de entrenador?",
                    fontSize = 13.sp,
                    color = TextPrimary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable { onForgotPasswordClick() }
                        .padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // ── Botón principal: INICIAR SESIÓN ──
                Button(
                    onClick = {
                        viewModel.loginUser(trainerName, password, onLoginSuccess)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PokeRed,
                        contentColor = Color.White
                    ),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 3.dp
                        )
                    } else {
                        Text(
                            text = "INICIAR SESIÓN",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.5.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Pokéball",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                // ── Link: Crear cuenta nueva ──
                Text(
                    text = "Crear cuenta nueva",
                    fontSize = 14.sp,
                    color = TextPrimary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable { onCreateAccountClick() }
                        .padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// ─────────────────────────────────────────
// Componente reutilizable: campo de texto
// ─────────────────────────────────────────
@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType,
    isPassword: Boolean
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 15.sp,
                color = TextSecondary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = IconTint,
                modifier = Modifier.size(26.dp)
            )
        },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = PokeRed,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        )
    )
}

// ─────────────────────────────────────────
// Componente: Barra de navegación inferior
// ─────────────────────────────────────────
@Composable
fun LoginBottomBar(onNavItemClick: (String) -> Unit = {}) {
    BottomAppBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.height(64.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoginNavItem(
                icon = Icons.Default.List,
                label = "POKÉDEX",
                isSelected = true,
                onClick = { onNavItemClick("pokedex") }
            )
            LoginNavItem(
                icon = Icons.Default.FavoriteBorder,
                label = "EQUIPO",
                isSelected = false,
                onClick = { onNavItemClick("equipo") }
            )
            LoginNavItem(
                icon = Icons.Default.Place,
                label = "REGIONES",
                isSelected = false,
                onClick = { onNavItemClick("regiones") }
            )
            LoginNavItem(
                icon = Icons.Default.Person,
                label = "PERFIL",
                isSelected = false,
                onClick = { onNavItemClick("perfil") }
            )
        }
    }
}

// ─────────────────────────────────────────
// Componente: Ítem del menú inferior
// ─────────────────────────────────────────
@Composable
fun LoginNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) PokeRed else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) PokeRed else Color.Gray,
            letterSpacing = 0.5.sp
        )
    }
}