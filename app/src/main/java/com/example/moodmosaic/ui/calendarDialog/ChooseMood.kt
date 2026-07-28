package com.example.moodmosaic.ui.calendarDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.ui.components.HeadlineMedium
import com.example.moodmosaic.ui.theme.toColor

@Composable
fun ChooseMood(
    selectedMoodId: Long,
    onMoodChange: (Long) -> Unit,
    moodDefinitions: List<MoodDefinition>,
    innerPadding: PaddingValues
) {
    val nPadding = PaddingValues(
        horizontal = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
        vertical = 0.dp,
    )

    HeadlineMedium(
        text = "Wie war der heutige Tag?",
        paddingValues = nPadding
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        moodDefinitions.forEach { mood ->

            SuggestionChip(
                onClick = { onMoodChange(mood.id) },

                border = SuggestionChipDefaults.suggestionChipBorder(
                    enabled = true,
                    borderColor =
                        if (selectedMoodId == mood.id)
                            mood.colorHex.toColor()
                        else
                            MaterialTheme.colorScheme.outlineVariant
                ),

                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor =
                        if (selectedMoodId == mood.id)
                            lerp(
                                MaterialTheme.colorScheme.surface,
                                mood.colorHex.toColor(),
                                0.25f
                            )
                        else
                            MaterialTheme.colorScheme.surface
                ),

                label = {
                    Row(
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(mood.colorHex.toColor())
                        )

                        Text(
                            text = mood.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            )
        }
    }
}