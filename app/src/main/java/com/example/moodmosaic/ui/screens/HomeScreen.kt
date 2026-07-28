package com.example.moodmosaic.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodmosaic.db.viewmodel.CalendarViewModel
import com.example.moodmosaic.ui.calendar.MonthlyOverview
import java.time.YearMonth

@Composable
fun HomeScreen(
    viewModel: CalendarViewModel,
    now: YearMonth
) {
    val calendarEntries by viewModel.items.collectAsState()
    val allMoods = viewModel.moodDefinitions.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            MonthlyOverview(now, calendarEntries, viewModel)
            MoodScreen( allMoods)
        }
    }
}