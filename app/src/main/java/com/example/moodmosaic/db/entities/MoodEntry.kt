package com.example.moodmosaic.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "mood_entries",
    indices = [
        Index(value = ["date"], unique = true),
        Index(value = ["moodId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = MoodDefinition::class,
            parentColumns = ["id"],
            childColumns = ["moodId"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)

data class MoodEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Long = 0,
    val date: LocalDate,
    val moodId: Long,
    val note: String?
)