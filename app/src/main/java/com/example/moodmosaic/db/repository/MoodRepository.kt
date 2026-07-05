package com.example.moodmosaic.db.repository

import com.example.moodmosaic.db.dao.MoodEntryDao
import com.example.moodmosaic.db.entities.MoodEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class MoodRepository(private val dao: MoodEntryDao) {

    fun getAllMoods() = dao.getAllMoods()

    fun getMoodsCurrentMonth(now: LocalDate = LocalDate.now()): Flow<List<MoodEntry>> {
        val start = now.withDayOfMonth(1)
        val end = now.withDayOfMonth(now.lengthOfMonth())

        return dao.getMoodsInBetween(start, end)
    }

    fun getMoodsLast7Days(now: LocalDate = LocalDate.now()): Flow<List<MoodEntry>> {
        val start = now.minusDays(6)

        return dao.getMoodsInBetween(start, now)
    }

    suspend fun getMood(date: LocalDate) = dao.getMoodByDate(date)

    suspend fun saveMood(entry: MoodEntry) {
        dao.insertMood(entry)
    }
}