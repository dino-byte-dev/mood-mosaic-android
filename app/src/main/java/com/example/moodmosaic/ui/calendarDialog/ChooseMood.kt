package com.example.moodmosaic.ui.calendarDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    HeadlineMedium(text = "Wie war der heutige Tag?", paddingValues = nPadding)

    LazyVerticalGrid(
        modifier = Modifier.padding(innerPadding).heightIn(max = 300.dp),
        columns = GridCells.Adaptive(minSize = 180.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(moodDefinitions) { mood ->
            Button(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor =
                        if (selectedMoodId == mood.id)
                            MaterialTheme.colorScheme.secondaryContainer
                        else
                            Color.Transparent,
                    contentColor =
                        Color.Unspecified
                ),
                onClick = {
                    onMoodChange(mood.id)
                },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .border(2.dp, MaterialTheme.colorScheme.background, RoundedCornerShape(6.dp))
                            .background(
                                color = mood.colorHex.toColor(),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = mood.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}