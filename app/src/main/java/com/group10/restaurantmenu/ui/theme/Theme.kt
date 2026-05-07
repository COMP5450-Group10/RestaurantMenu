package com.group10.restaurantmenu.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightBackground = Color(0xFFF5F5F7)
val DarkText = Color(0xFF1D1D1F)
val SecondaryText = Color(0xFF86868B)
val SurfaceWhite = Color(0xFFFFFFFF)
val Outline = Color(0xFFD2D2D7)
val PopularOrange = Color(0xFFE65100)
val PopularBackground = Color(0xFFFFF3E0)
val ErrorRed = Color(0xFFFF3B30)
val SuccessGreen = Color(0xFF34C759)

private val LightColorScheme = lightColorScheme(
    primary = DarkText,
    onPrimary = Color.White,
    secondary = SecondaryText,
    surface = SurfaceWhite,
    background = LightBackground,
    onSurface = DarkText,
    onBackground = DarkText,
    outline = Outline,
    error = ErrorRed,
)

@Composable
fun RestaurantMenuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        content = content
    )
}
