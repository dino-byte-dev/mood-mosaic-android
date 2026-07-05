package com.example.moodmosaic

import com.example.moodmosaic.db.dao.MoodEntryDao
import com.example.moodmosaic.db.entities.MoodEntry
import com.example.moodmosaic.db.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import java.time.LocalDate
import org.junit.Test

class MoodRepositoryTest {

    private lateinit var dao: FakeMoodEntryDao
    private lateinit var repository: MoodRepository

    @Before
    fun setup() {
        dao = FakeMoodEntryDao()
        repository = MoodRepository(dao)
    }

    @Test
    fun saveMood_insertsData() = runTest {
        val mood = MoodEntry(
            date = LocalDate.now(),
            moodId = 1,
            note = "test"
        )

        repository.saveMood(mood)

        val result = repository.getAllMoods().first()

        assertEquals(1, result.size)
        assertEquals(mood, result.first())
    }

    @Test
    fun getMoodsCurrentMonth_filtersCorrectly() = runTest {
        val now = LocalDate.of(2027, 7, 15)

        dao.moods.addAll(
            listOf(
                MoodEntry(0, LocalDate.of(2027, 7, 1), 1, null),
                MoodEntry(1, LocalDate.of(2027, 7, 10), 1, null),
                MoodEntry(2, LocalDate.of(2027, 6, 30), 1, null)
            )
        )

        val result = repository.getMoodsCurrentMonth(now).first()

        assertEquals(2, result.size)
    }

    @Test
    fun getMoodsLast7Days_returnsOnlyRange() = runTest {
        val today = LocalDate.of(2026, 7, 10)

        dao.moods.addAll(
            listOf(
                MoodEntry(3, today.minusDays(1), 1, null),
                MoodEntry(4, today.minusDays(2), 1, null),
                MoodEntry(5, today.minusDays(3), 1, null),
                MoodEntry(6, today.minusDays(4), 1, null),
                MoodEntry(7, today.minusDays(5), 1, null),
                MoodEntry(8, today.minusDays(6), 1, null),
                MoodEntry(9, today.minusDays(7), 1, null),
                MoodEntry(10, today.minusDays(8), 1, null),
                MoodEntry(11, today.minusDays(9), 1, null),
                MoodEntry(12, today.minusDays(10), 1, null)
            )
        )

        val result = repository.getMoodsLast7Days(today).first()

        assertEquals(6, result.size)
    }
}

class FakeMoodEntryDao : MoodEntryDao {

    val moods = mutableListOf<MoodEntry>()

    override fun getAllMoods(): Flow<List<MoodEntry>> =
        flowOf(moods)

    override fun getMoodByDate(date: LocalDate): Flow<MoodEntry?> =
        flowOf(moods.find { it.date == date })

    override fun getMoodsInBetween(start: LocalDate, end: LocalDate): Flow<List<MoodEntry>> =
        flow {
            emit(moods.filter { it.date >= start && it.date <= end })
        }

    override suspend fun insertMood(item: MoodEntry) {
        moods.removeIf { it.date == item.date }
        moods.add(item)
    }

    override suspend fun updateMood(item: MoodEntry) {}

    override suspend fun deleteMood(item: MoodEntry) {
        moods.removeIf { it.date == item.date }
    }
}

