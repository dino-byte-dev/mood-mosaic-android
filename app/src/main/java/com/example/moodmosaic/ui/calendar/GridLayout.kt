package com.example.moodmosaic.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.db.viewmodel.CalendarEntry
import com.example.moodmosaic.ui.calendarDialog.CreateEntry
import com.example.moodmosaic.ui.theme.calendarBufferDay
import com.example.moodmosaic.ui.theme.calendarEmptyDay
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun GridLayout(
    padding: PaddingValues,
    days: List<CalendarEntry>,
    moodDefinitions: List<MoodDefinition>,
    now: YearMonth,
    onSave: (CalendarEntry, String, Long) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableStateOf<CalendarEntry?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(padding).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        days.chunked(7).forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                week.forEach { day ->
                    val color = when {
                        day.date.month != now.month -> MaterialTheme.colorScheme.calendarBufferDay

                        day.mood == null -> MaterialTheme.colorScheme.calendarEmptyDay

                        else -> Color(
                            day.mood.colorHex
                                .removePrefix("#")
                                .toLong(16) or 0xFF000000
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .sizeIn(maxWidth = 55.dp, maxHeight = 55.dp)
                            .background(
                                color = color,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable {
                                showDialog = true
                                selectedDay = day
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.date.dayOfMonth.toString(),
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    }
                }


            }
        }
    }

    // Null-safe durch ?. (im Gegensatz zu Java)
    if (showDialog && selectedDay?.date?.month == now.month) {
        CreateEntry(
            padding = padding,
            dateString = selectedDay?.date?.format(DateTimeFormatter.ofPattern("EEEE, dd. MMMM uuu"))
                ?: "Unbekannt",
            initialNote = selectedDay?.mood?.note ?: "",
            moodDefinitions = moodDefinitions,
            initialMood = selectedDay?.mood?.moodId ?: 0L,
            onDismiss = { showDialog = false },
            onSave = { newNote, newMood ->
                showDialog = false

                onSave(selectedDay!!, newNote, newMood)
            }
        )
    }
}