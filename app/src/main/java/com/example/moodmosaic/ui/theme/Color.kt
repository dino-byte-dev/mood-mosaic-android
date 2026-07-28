package com.example.moodmosaic.ui.theme

import androidx.compose.ui.graphics.Color

// Light theme
val GreenPrimaryLight = Color(0xFF306A4E)
val GreenOnPrimaryLight = Color(0xFFE6FFEE)
val GreenPrimaryContainerLight = Color(0xFFB3F0CD)
val GreenOnPrimaryContainerLight = Color(0xFF225C41)

val GreenSecondaryLight = Color(0xFF4E6457)
val GreenOnSecondaryLight = Color(0xFFE6FFEE)
val GreenSecondaryContainerLight = Color(0xFFD0E8D8)
val GreenOnSecondaryContainerLight = Color(0xFF41574A)

val TealTertiaryLight = Color(0xFF2D676C)
val TealOnTertiaryLight = Color(0xFFE6FDFF)
val TealTertiaryContainerLight = Color(0xFFB5F0F5)
val TealOnTertiaryContainerLight = Color(0xFF1F5C60)

val BackgroundLight = Color(0xFFF7FAF5)
val OnBackgroundLight = Color(0xFF2B352F)

val SurfaceLight = Color(0xFFF7FAF5)
val OnSurfaceLight = Color(0xFF2B352F)

val SurfaceVariantLight = Color(0xFFDBE5DC)
val OnSurfaceVariantLight = Color(0xFF58615B)

val OutlineLight = Color(0xFF737D76)
val OutlineVariantLight = Color(0xFFAAB4AC)

val ErrorLight = Color(0xFFB3261E)
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFF9DEDC)
val OnErrorContainerLight = Color(0xFF410E0B)

val InverseSurfaceLight = Color(0xFF0B0F0C)
val InverseOnSurfaceLight = Color(0xFFDEE8DF)
val InversePrimaryLight = Color(0xFFA3D1B6)

// Dark theme
val GreenPrimaryDark = Color(0xFFA3D1B6)
val GreenOnPrimaryDark = Color(0xFF1E4834)
val GreenPrimaryContainerDark = Color(0xFF315B45)
val GreenOnPrimaryContainerDark = Color(0xFFBFEED2)

val GreenSecondaryDark = Color(0xFFB4CCBC)
val GreenOnSecondaryDark = Color(0xFF304539)
val GreenSecondaryContainerDark = Color(0xFF2B4034)
val GreenOnSecondaryContainerDark = Color(0xFFADC5B5)

val TealTertiaryDark = Color(0xFFD1FBFF)
val TealOnTertiaryDark = Color(0xFF296469)
val TealTertiaryContainerDark = Color(0xFFB5F0F5)
val TealOnTertiaryContainerDark = Color(0xFF1F5C60)

val BackgroundDark = Color(0xFF0B0F0C)
val OnBackgroundDark = Color(0xFFDEE8DF)

val SurfaceDark = Color(0xFF0B0F0C)
val OnSurfaceDark = Color(0xFFDEE8DF)

val SurfaceVariantDark = Color(0xFF1F2822)
val OnSurfaceVariantDark = Color(0xFFA4AEA5)

val OutlineDark = Color(0xFF6E7871)
val OutlineVariantDark = Color(0xFF414A44)

val ErrorDark = Color(0xFFF2B8B5)
val OnErrorDark = Color(0xFF601410)
val ErrorContainerDark = Color(0xFF8C1D18)
val OnErrorContainerDark = Color(0xFFF9DEDC)

val InverseSurfaceDark = Color(0xFFF7FAF5)
val InverseOnSurfaceDark = Color(0xFF2B352F)
val InversePrimaryDark = Color(0xFF306A4E)

// Eigene App-Farben
val CalendarEmptyDay = Color(0xFFE0E0E0)
val CalendarBufferDay = Color(0xFF808080)
val TrueBlack = Color(0xFF000000)

fun Color.toHex(): String {
    return String.format(
        "#%02X%02X%02X%02X",
        (alpha * 255).toInt(),
        (red * 255).toInt(),
        (green * 255).toInt(),
        (blue * 255).toInt()
    )
}

fun String.toColor(): androidx.compose.ui.graphics.Color {
    val hex = removePrefix("#")

    return when (hex.length) {
        6 -> Color((0xFF000000 or hex.toLong(16)).toULong().toInt())
        8 -> Color(hex.toULong(16).toInt())
        else -> throw IllegalArgumentException("Ungültiger Farbwert: $this")
    }
}