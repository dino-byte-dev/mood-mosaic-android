package com.example.moodmosaic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moodmosaic.db.AppDatabase
import com.example.moodmosaic.db.repository.MoodDefinitionRepository
import com.example.moodmosaic.db.repository.MoodEntryRepository
import com.example.moodmosaic.db.viewmodel.CalendarViewModel
import com.example.moodmosaic.db.viewmodel.CalendarViewModelFactory
import com.example.moodmosaic.provider.CalendarLogic
import com.example.moodmosaic.ui.screens.HomeScreen
import com.example.moodmosaic.ui.theme.MoodMosaicTheme
import java.time.LocalDate
import java.time.YearMonth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // 1. Instanz der Datenbank holen und dem lifecycleScope der Activity übergeben
        val database = AppDatabase.getInstance(applicationContext, lifecycleScope)
        val moodEntryDao = database.moodEntryDao()
        val moodDefinitionDao = database.moodDefinitionDao()

        // 2. Calenderlogik holen
        val calendar = CalendarLogic()
        val now: YearMonth = YearMonth.now()
        val days: List<LocalDate> = calendar.getCalendarGrid(now)

        // 3. Repositories deklarieren
        val mdRepository = MoodDefinitionRepository(moodDefinitionDao)
        val meRepository = MoodEntryRepository(moodEntryDao)

        // 4. ViewModel für Kalendereinträge erzeugen und mit Repository anreichern
        val viewModel: CalendarViewModel by viewModels {
            CalendarViewModelFactory(
                meRepository,
                mdRepository,
                days.first(),
                days.last()
            )
        }

        setContent {
            MoodMosaicTheme {
                HomeScreen(viewModel, now)
            }
        }
    }
}