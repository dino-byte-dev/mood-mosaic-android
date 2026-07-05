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

@Database(entities = [MoodEntry::class, MoodDefinition::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moodEntryDao(): MoodEntryDao
    abstract fun moodDefinitionDao(): MoodDefinitionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
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
                    .addCallback(AppDatabaseCallback())
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

class AppDatabaseCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        Log.d("AppDatabase", "Database was created")

        // todo initial data seeding
        // todo seedDefaultMoods()
        // todo insert default moods
        // todo initDefaultData()
    }
}