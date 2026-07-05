package com.example.moodmosaic

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moodmosaic.db.AppDatabase
import com.example.moodmosaic.db.dao.MoodEntryDao
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.db.entities.MoodEntry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.time.LocalDate
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class MoodEntryDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: MoodEntryDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.moodEntryDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    private suspend fun insertDefaultMoodDefinition(
        id: Long = 1,
        name: String = "Happy"
    ) {
        db.moodDefinitionDao().insert(
            MoodDefinition(
                id = id,
                name = name,
                colorHex = "#FF0000"
            )
        )
    }

    private fun moodEntry(
        entryId: Long = 0,
        date: LocalDate = LocalDate.now(),
        moodId: Long = 1,
        note: String? = null
    ) = MoodEntry(
        entryId = entryId,
        date = date,
        moodId = moodId,
        note = note
    )

    @Test
    fun insertMood_returnsInsertedMood() = runTest {
        insertDefaultMoodDefinition()

        val mood = moodEntry(entryId = 123, note = "good day")

        dao.insertMood(mood)

        val result = dao.getMoodByDate(mood.date).first()

        assertEquals(
            mood,
            result
        )
    }

    @Test
    fun updateMood_updatesNote() = runTest {
        insertDefaultMoodDefinition()

        val mood = moodEntry(
            entryId = 123,
            note = "sad day"
        )

        dao.insertMood(mood)

        val updated = mood.copy(
            note = "Heute war ein richtig guter Tag."
        )

        dao.updateMood(updated)

        val result = dao.getMoodByDate(mood.date).first()

        assertEquals(updated, result)
    }

    @Test
    fun deleteMood_removesEntry() = runTest {
        insertDefaultMoodDefinition()

        val mood = moodEntry(entryId = 123)

        dao.insertMood(mood)
        dao.deleteMood(mood)

        assertEquals(
            0,
            dao.getAllMoods().first().size
        )
    }

    @Test
    fun getAllMoods_returnsAllMoods() = runTest {
        insertDefaultMoodDefinition()

        dao.insertMood(moodEntry(entryId = 1, date = LocalDate.of(2027, 7, 1)))
        dao.insertMood(moodEntry(entryId = 2, date = LocalDate.of(2027, 7, 2)))
        dao.insertMood(moodEntry(entryId = 3, date = LocalDate.of(2027, 7, 3)))

        val result = dao.getAllMoods().first()

        assertEquals(3, result.size)
    }

    @Test
    fun getMoodByDate_returnsCorrectMood() = runTest {
        insertDefaultMoodDefinition()

        val mood = moodEntry(
            date = LocalDate.of(2027, 7, 15)
        )

        dao.insertMood(mood)

        val result = dao.getMoodByDate(mood.date).first()

        assertEquals(
            mood.copy(entryId = result!!.entryId),
            result
        )
    }

    @Test
    fun getMoodsInBetween_returnsOnlyMatchingEntries() = runTest {
        insertDefaultMoodDefinition()

        dao.insertMood(moodEntry(date = LocalDate.of(2027, 7, 1)))
        dao.insertMood(moodEntry(date = LocalDate.of(2027, 7, 10)))
        dao.insertMood(moodEntry(date = LocalDate.of(2027, 8, 1)))

        val result = dao.getMoodsInBetween(
            LocalDate.of(2027, 7, 1),
            LocalDate.of(2027, 7, 31)
        ).first()

        assertEquals(2, result.size)
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertMoodWithoutDefinition_fails() = runTest {
        dao.insertMood(
            moodEntry(
                moodId = 999,
                note = "bad"
            )
        )
    }
}