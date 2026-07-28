package com.example.moodmosaic.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.ui.theme.calendarEmptyDay
import com.example.moodmosaic.ui.theme.toColor

@Composable
fun MoodScreen(
    moodDefinitions: List<MoodDefinition>
) {
    val padding = PaddingValues(15.dp)

    Row(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .border(
                2.dp,
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(14.dp)
            )
            .background(
                MaterialTheme.colorScheme.calendarEmptyDay,
                RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 4.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        moodDefinitions.forEach { mood ->
            if (mood.name != "Keine Stimmung") {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .sizeIn(maxWidth = 33.dp, maxHeight = 33.dp)
                            .aspectRatio(1f)
                            .background(
                                color = mood.colorHex.toColor(),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Text(
                        text = mood.name,
                        modifier = Modifier.padding(top = 4.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
