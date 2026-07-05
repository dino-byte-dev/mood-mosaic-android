package com.example.moodmosaic.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moodmosaic.db.entities.MoodEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface MoodEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(item: MoodEntry)

    @Update
    suspend fun updateMood(item: MoodEntry)

    @Delete
    suspend fun deleteMood(item: MoodEntry)

    @Query("SELECT * FROM mood_entries ORDER BY date DESC")
    fun getAllMoods(): Flow<List<MoodEntry>>

    @Query("SELECT * FROM mood_entries WHERE date = :date")
    fun getMoodByDate(date: LocalDate): Flow<MoodEntry?>

    @Query("SELECT * FROM mood_entries WHERE date BETWEEN :start AND :end ORDER BY date ASC")
    fun getMoodsInBetween(start: LocalDate, end: LocalDate): Flow<List<MoodEntry>>
}