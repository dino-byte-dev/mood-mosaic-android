package com.example.moodmosaic.db.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moodmosaic.db.entities.MoodEntry
import com.example.moodmosaic.db.repository.MoodDefinitionRepository
import com.example.moodmosaic.db.repository.MoodEntryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarViewModel(
    private val repository: MoodEntryRepository,
    private val mdRepository: MoodDefinitionRepository,
    start: LocalDate,
    end: LocalDate
) : ViewModel() {
    val moodDefinitions = mdRepository
        .getMoodDefinitions()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val items = repository
        .getMoodsInBetweenWithDefinition(start, end)
        .map { moods ->

            val byDate = moods.associateBy { it.date }

            generateSequence(start) { date ->
                if (date < end) date.plusDays(1) else null
            }.map { date ->
                CalendarEntry(
                    date = date,
                    mood = byDate[date]
                )
            }.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insert(entry: MoodEntry) {
        viewModelScope.launch {
            repository.saveMood(entry)
        }
    }

    fun update(entry: MoodEntry) {
        viewModelScope.launch {
            repository.update(entry)
        }
    }

    fun delete(entry: MoodEntry) {
        viewModelScope.launch {
            repository.delete(entry)
        }
    }
}

class CalendarViewModelFactory(
    private val repository: MoodEntryRepository,
    private val mdRepository: MoodDefinitionRepository,
    val start: LocalDate,
    val end: LocalDate
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            return CalendarViewModel(repository, mdRepository, start, end) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}