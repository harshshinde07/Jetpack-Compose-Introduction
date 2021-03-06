package com.apps.harsh.jetpackcompose

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme

val green = Color(0xFF1EB980)
private val themeColors = ColorPalette(
    primary = green,
    surface = Color.DarkGray,
    onSurface = green
)

@Composable
fun MyAppTheme(children: @Composable() () -> Unit) {
    MaterialTheme(colors = themeColors) {
        children()
    }
}