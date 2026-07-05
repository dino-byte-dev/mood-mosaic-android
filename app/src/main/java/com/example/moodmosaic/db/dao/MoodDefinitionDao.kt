package com.example.moodmosaic.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.db.entities.MoodEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDefinitionDao {

    @Insert
    suspend fun insert(item: MoodDefinition)

    @Update
    suspend fun update(item: MoodDefinition)

    @Delete
    suspend fun delete(item: MoodDefinition)

    @Query("SELECT * FROM mood_definitions ORDER BY id ASC")
    fun getMoodDefinitions(): Flow<List<MoodDefinition>>
}