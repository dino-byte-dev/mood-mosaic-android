package com.example.moodmosaic.db.viewmodel

import com.example.moodmosaic.db.entities.MoodEntryWithDefinition
import java.time.LocalDate

data class CalendarDay(
    val date: LocalDate,
    val mood: MoodEntryWithDefinition?
)