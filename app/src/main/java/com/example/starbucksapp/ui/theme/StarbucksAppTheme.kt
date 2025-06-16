package com.example.starbucksapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF00704A),
    primaryVariant = Color(0xFF005835),
    secondary = Color(0xFFB2DFDB)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF00704A),
    primaryVariant = Color(0xFF005835),
    secondary = Color(0xFF4DB6AC)
)

@Composable
fun StarbucksAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
