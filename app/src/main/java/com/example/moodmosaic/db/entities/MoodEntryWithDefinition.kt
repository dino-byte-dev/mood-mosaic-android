package com.example.moodmosaic.db.entities

import java.time.LocalDate

data class MoodEntryWithDefinition(
    val entryId: Long,
    val date: LocalDate,
    val moodId: Long,
    val note: String?,
    val colorHex: String
)