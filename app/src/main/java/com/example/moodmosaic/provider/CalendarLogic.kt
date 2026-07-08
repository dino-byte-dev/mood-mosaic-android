package com.example.moodmosaic.provider

import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

class CalendarLogic {

    fun getToday(): LocalDate {
        return LocalDate.now()
    }

    fun getFirstAndLastDayOfMonth(targetMonth: YearMonth): Pair<LocalDate, LocalDate> {
        return Pair(
            targetMonth.atDay(1),
            targetMonth.atEndOfMonth()
        )
    }

    fun getDaysInMonth(targetMonth: YearMonth): List<LocalDate> {
        val (firstDay, lastDay) = getFirstAndLastDayOfMonth(targetMonth)
        val daysBetween = ChronoUnit.DAYS.between(firstDay, lastDay)

        return (0..daysBetween).map { firstDay.plusDays(it) }
    }

    fun getPreviousMonthBufferDays(targetMonth: YearMonth): List<LocalDate> {
        val firstDayOfMonth:  LocalDate = targetMonth.atDay(1)
        val daysToSubtract = firstDayOfMonth.dayOfWeek.value - 1

        val bufferDays = mutableListOf<LocalDate>()

        for (i in daysToSubtract downTo 1) {
            bufferDays.add(firstDayOfMonth.minusDays(i.toLong()))
        }
        return bufferDays
    }

    fun getNextMonthBufferDays(targetMonth: YearMonth): List<LocalDate> {
        val totalCells = 42

        val startBufferCount: Int = targetMonth.atDay(1).dayOfWeek.value - 1
        val currentMonthDaysCount: Int = targetMonth.lengthOfMonth()
        val remainingCells = totalCells - (currentMonthDaysCount + startBufferCount)

        // Der erste Tag des Folgemonats ist die Basis
        val firstDayOfNextMonth = targetMonth.atEndOfMonth().plusDays(1)

        val bufferDays = mutableListOf<LocalDate>()
        for (i in 0 until remainingCells) {
            bufferDays.add(firstDayOfNextMonth.plusDays(i.toLong()))
        }
        return bufferDays
    }
}