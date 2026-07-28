import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.ui.theme.calendarBufferDay
import com.example.moodmosaic.ui.theme.calendarEmptyDay
import com.example.moodmosaic.ui.theme.toHex

private data class PreviewColor(
    val name: String,
    val color: Color,
    val onColor: Color
)

@Preview
@Composable
fun ColorPreview() {

    val scheme = MaterialTheme.colorScheme

    val colors = listOf(
        PreviewColor("primary", scheme.primary, scheme.onPrimary),
        PreviewColor("onPrimary", scheme.onPrimary, scheme.primary),
        PreviewColor("primaryContainer", scheme.primaryContainer, scheme.onPrimaryContainer),
        PreviewColor("onPrimaryContainer", scheme.onPrimaryContainer, scheme.primaryContainer),

        PreviewColor("secondary", scheme.secondary, scheme.onSecondary),
        PreviewColor("onSecondary", scheme.onSecondary, scheme.secondary),
        PreviewColor("secondaryContainer", scheme.secondaryContainer, scheme.onSecondaryContainer),
        PreviewColor("onSecondaryContainer", scheme.onSecondaryContainer, scheme.secondaryContainer),

        PreviewColor("tertiary", scheme.tertiary, scheme.onTertiary),
        PreviewColor("onTertiary", scheme.onTertiary, scheme.tertiary),
        PreviewColor("tertiaryContainer", scheme.tertiaryContainer, scheme.onTertiaryContainer),
        PreviewColor("onTertiaryContainer", scheme.onTertiaryContainer, scheme.tertiaryContainer),

        PreviewColor("background", scheme.background, scheme.onBackground),
        PreviewColor("onBackground", scheme.onBackground, scheme.background),

        PreviewColor("surface", scheme.surface, scheme.onSurface),
        PreviewColor("onSurface", scheme.onSurface, scheme.surface),
        PreviewColor("surfaceVariant", scheme.surfaceVariant, scheme.onSurfaceVariant),
        PreviewColor("onSurfaceVariant", scheme.onSurfaceVariant, scheme.surfaceVariant),

        PreviewColor("outline", scheme.outline, scheme.surface),
        PreviewColor("outlineVariant", scheme.outlineVariant, scheme.surface),

        PreviewColor("error", scheme.error, scheme.onError),
        PreviewColor("onError", scheme.onError, scheme.error),
        PreviewColor("errorContainer", scheme.errorContainer, scheme.onErrorContainer),
        PreviewColor("onErrorContainer", scheme.onErrorContainer, scheme.errorContainer),

        PreviewColor("inverseSurface", scheme.inverseSurface, scheme.inverseOnSurface),
        PreviewColor("inverseOnSurface", scheme.inverseOnSurface, scheme.inverseSurface),
        PreviewColor("inversePrimary", scheme.inversePrimary, scheme.inverseSurface),

        PreviewColor("scrim", scheme.scrim, Color.White),
        PreviewColor("surfaceTint", scheme.surfaceTint, scheme.onSurface),

        PreviewColor("calendarEmptyDay", scheme.calendarEmptyDay, scheme.onSurface),
        PreviewColor("calendarBufferDay", scheme.calendarBufferDay, scheme.onSurface)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(colors) { item ->

            Card {
                Column {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(96.dp)
                            .background(item.color)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Aa 123",
                            color = item.onColor,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.labelLarge
                        )

                        Text(
                            text = item.color.toHex(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun logColorScheme() {
    val colors = mapOf(
        "primary" to MaterialTheme.colorScheme.primary,
        "onPrimary" to MaterialTheme.colorScheme.onPrimary,
        "primaryContainer" to MaterialTheme.colorScheme.primaryContainer,
        "onPrimaryContainer" to MaterialTheme.colorScheme.onPrimaryContainer,

        "secondary" to MaterialTheme.colorScheme.secondary,
        "onSecondary" to MaterialTheme.colorScheme.onSecondary,
        "secondaryContainer" to MaterialTheme.colorScheme.secondaryContainer,
        "onSecondaryContainer" to MaterialTheme.colorScheme.onSecondaryContainer,

        "tertiary" to MaterialTheme.colorScheme.tertiary,
        "onTertiary" to MaterialTheme.colorScheme.onTertiary,
        "tertiaryContainer" to MaterialTheme.colorScheme.tertiaryContainer,
        "onTertiaryContainer" to MaterialTheme.colorScheme.onTertiaryContainer,

        "background" to MaterialTheme.colorScheme.background,
        "onBackground" to MaterialTheme.colorScheme.onBackground,

        "surface" to MaterialTheme.colorScheme.surface,
        "onSurface" to MaterialTheme.colorScheme.onSurface,
        "surfaceVariant" to MaterialTheme.colorScheme.surfaceVariant,
        "onSurfaceVariant" to MaterialTheme.colorScheme.onSurfaceVariant,

        "outline" to MaterialTheme.colorScheme.outline,
        "outlineVariant" to MaterialTheme.colorScheme.outlineVariant,

        "error" to MaterialTheme.colorScheme.error,
        "onError" to MaterialTheme.colorScheme.onError,
        "errorContainer" to MaterialTheme.colorScheme.errorContainer,
        "onErrorContainer" to MaterialTheme.colorScheme.onErrorContainer,

        "inverseSurface" to MaterialTheme.colorScheme.inverseSurface,
        "inverseOnSurface" to MaterialTheme.colorScheme.inverseOnSurface,
        "inversePrimary" to MaterialTheme.colorScheme.inversePrimary,

        "scrim" to MaterialTheme.colorScheme.scrim,
        "surfaceTint" to MaterialTheme.colorScheme.surfaceTint,

        "calendarEmptyDay" to MaterialTheme.colorScheme.calendarEmptyDay,
        "calendarBufferDay" to MaterialTheme.colorScheme.calendarBufferDay
    )

    val output = buildString {
        colors.forEach { (name, color) ->
            appendLine(
                "$name = Color(${color.toHex()})"
            )
        }
    }

    Log.d("COLOR_SCHEME", "\n$output")
}