package com.example.moodmosaic.ui.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.db.entities.MoodEntry
import com.example.moodmosaic.db.viewmodel.CalendarEntry
import com.example.moodmosaic.db.viewmodel.CalendarViewModel
import com.example.moodmosaic.ui.components.HeadlineLarge
import java.time.YearMonth

@Composable
fun MonthlyOverview(
    now: YearMonth,
    calendarEntries: List<CalendarEntry>,
    viewModel: CalendarViewModel
) {
    val moodDefinitions by viewModel.moodDefinitions.collectAsState()
    val padding = PaddingValues(15.dp)

    HeadlineLarge("${now.month} ${now.year}", padding)

    GridLayout(padding, calendarEntries, moodDefinitions, now, onSave = { day, note, moodId ->
        if (day.mood == null) {
            viewModel.insert(MoodEntry(date = day.date, moodId = moodId, note = note))
        } else {
            viewModel.update(
                MoodEntry(
                    entryId = day.mood.entryId,
                    date = day.date,
                    moodId = moodId,
                    note = note
                )
            )
        }
    })
}