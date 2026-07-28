package com.example.moodmosaic.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.ui.calendarDialog.ChooseMood
import com.example.moodmosaic.ui.components.HeadlineLarge
import com.example.moodmosaic.ui.theme.CalendarEmptyDay
import com.example.moodmosaic.ui.theme.MoodMosaicTheme
import com.example.moodmosaic.ui.theme.toHex

@Preview(showBackground = true)
@Composable
fun DefaultMoodsPreview() {
    MoodMosaicTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .padding(top = 10.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp, 0.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                val pad = PaddingValues(6.dp)

                val moods: List<MoodDefinition> = listOf(
                    MoodDefinition(id = 0L, name = "Keine Stimmung", colorHex = CalendarEmptyDay.toHex()),
                    MoodDefinition(id = 1L, name = "Sehr schlecht", colorHex = "#E57373"),
                    MoodDefinition(id = 2L, name = "Schlecht", colorHex = "#FFB74D"),
                    MoodDefinition(id = 3L, name = "Neutral", colorHex = "#64B5F6"),
                    MoodDefinition(id = 4L, name = "Gut", colorHex = "#FFF176"),
                    MoodDefinition(id = 5L, name = "Sehr gut", colorHex = "#81C784")
                )

                ChooseMood(
                    selectedMoodId = 1,
                    onMoodChange = {},
                    innerPadding = pad,
                    moodDefinitions = moods
                )
            }
        }
    }
}