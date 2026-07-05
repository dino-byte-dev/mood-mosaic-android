package com.example.moodmosaic.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_definitions")
data class MoodDefinition(
    @PrimaryKey
    val id: Long,
    val name: String,
    val colorHex: String
)