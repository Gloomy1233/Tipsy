package com.example.composeApp.utility

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val gradientPink = Color(0xFFEDCEFF)  // Purple
val gradientOrange = Color(0xFFE8BD7C)

val gradient = Brush.linearGradient(
    colors = listOf(gradientPink, gradientOrange),
    start = Offset.Zero,
    end = Offset.Infinite
)
// Dark Purple
val primaryDark = Color(0xFF262838) // Teal
val primaryDarkLighter = Color(0xFF46495B) // Teal
val primaryDarkOpacity = Color(0xBF262838)
val primaryOrange = Color(0xFFE8827C)
val purpleApp = Color(0xFF5E3FBE) // Light Gray
val redApp = Color(0xFFFF5656) // Red
val blueApp = Color(0xFF58B3F5) // White text
val greenApp = Color(0xFFB1F8AF) // Black text
