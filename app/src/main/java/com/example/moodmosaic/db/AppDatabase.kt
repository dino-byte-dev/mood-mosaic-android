package com.example.moodmosaic.db

import androidx.room.Database
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moodmosaic.db.dao.MoodDefinitionDao
import com.example.moodmosaic.db.dao.MoodEntryDao
import com.example.moodmosaic.db.entities.MoodDefinition
import com.example.moodmosaic.db.entities.MoodEntry
import com.example.moodmosaic.ui.theme.CalendarEmptyDay
import com.example.moodmosaic.ui.theme.toHex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [MoodEntry::class, MoodDefinition::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moodEntryDao(): MoodEntryDao
    abstract fun moodDefinitionDao(): MoodDefinitionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope) { INSTANCE!!.moodDefinitionDao() })
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

class AppDatabaseCallback(private val scope: CoroutineScope, private val databaseProvider: () -> MoodDefinitionDao) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val defaultMoods = listOf(
            MoodDefinition(id = 1L, name = "Sehr schlecht", colorHex = "#E47272"),
            MoodDefinition(id = 2L, name = "Schlecht", colorHex = "#FC891F"),
            MoodDefinition(id = 3L, name = "Eher schlecht", colorHex = "#FCC52C"),
            MoodDefinition(id = 4L, name = "Neutral", colorHex = "#64B3F6"),
            MoodDefinition(id = 5L, name = "Eher gut", colorHex = "#12AD97"),
            MoodDefinition(id = 6L, name = "Gut", colorHex = "#80C683"),
            MoodDefinition(id = 7L, name = "Sehr gut", colorHex = "#4CAF50"),
            MoodDefinition(id = 8L, name = "Keine Angabe", colorHex = CalendarEmptyDay.toHex()),
        )

        Log.d("AppDatabase", "Database was created")

        scope.launch {
            val dao = databaseProvider()

            defaultMoods.forEach { mood ->
                dao.insert(mood)
            }
        }
    }
}