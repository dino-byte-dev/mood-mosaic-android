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
            MoodDefinition(id = 1L, name = "Sehr schlecht 😖", colorHex = "#E57373"),
            MoodDefinition(id = 2L, name = "Schlecht 😞", colorHex = "#FFB74D"),
            MoodDefinition(id = 3L, name = "Neutral 😐", colorHex = "#FFF176"),
            MoodDefinition(id = 4L, name = "Gut 🙂", colorHex = "#81C784"),
            MoodDefinition(id = 5L, name = "Sehr gut 😊", colorHex = "#64B5F6")
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