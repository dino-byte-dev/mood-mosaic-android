package com.example.moodmosaic

import com.example.moodmosaic.provider.CalendarLogic
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

class CalendarLogicTest {

    private val calendarLogic = CalendarLogic()

    @Test
    fun `Februar 2024 (Schaltjahr) hat exakt 29 Tage`() {
        val leapYearFebruary = YearMonth.of(2024, 2)

        val days = calendarLogic.getDaysInMonth(leapYearFebruary)

        assertEquals(29, days.size)
        assertEquals(29, days.last().dayOfMonth)
    }

    @Test
    fun `Februar 2026 (kein Schaltjahr) hat exakt 28 Tage`() {
        val leapYearFebruary = YearMonth.of(2026, 2)

        val days = calendarLogic.getDaysInMonth(leapYearFebruary)

        assertEquals(28, days.size)
        assertEquals(28, days.last().dayOfMonth)
    }

    @Test
    fun `Mai 2026 startet am Freitag und erzeugt exakt 4 (pre-)Puffer-Tage`() {
        // Hinweis - Der Mai 2026 startete an einem Freitag
        // Formel: 5 - 1 = 4 Puffer-Tage (Mo, Di, Mi, Do)
        val may2026 = YearMonth.of(2026, Month.MAY)

        val previousBuffer = calendarLogic.getPreviousMonthBufferDays(may2026)

        assertEquals(4, previousBuffer.size)
        // Der letzte Puffertag muss der 30. April sein
        assertEquals(30, previousBuffer.last().dayOfMonth)
        assertEquals(Month.APRIL.value, previousBuffer.last().monthValue)
    }

    @Test
    fun `Juli 2026 startet am 1ten und endet am 31ten Juli`() {
        val jul2026 = YearMonth.of(2026, Month.JULY)

        val firstAndLastDay = calendarLogic.getFirstAndLastDayOfMonth(jul2026)
        val firstDay = firstAndLastDay.first
        val lastDay = firstAndLastDay.second

        // Der letzte Tag muss der 31. Juli sein
        assertEquals(31, lastDay.dayOfMonth)
        assertEquals(Month.JULY, lastDay.month)
        // Der erste Tag muss der 1. Juli sein
        assertEquals(1, firstDay.dayOfMonth)
        assertEquals(Month.JULY, firstDay.month)
    }

    @Test
    fun `Alle Tage im Monat Juli 2026 (List von LocalDates)`() {
        val jul2026 = YearMonth.of(2026, Month.JULY)

        val daysInMonth = calendarLogic.getDaysInMonth(jul2026)
        val expected: List<LocalDate> = (1..31).map { day ->
            LocalDate.of(2026, 7, day)
        }

        assertEquals(expected, daysInMonth)
    }

    @Test
    fun `Juli 2026 endet auf den 31ten und erzeugt exakt 9 (post-)Puffer-Tage`() {
        // Hinweis - Der Juli 2026 endete am Freitag, dem 31.07.2026
        // Formel: 42- 31 - 2 = 9 Puffer-Tage (Sa, So, Mo, Di, Mi, Do, Fr, Sa, So)
        val jul2026 = YearMonth.of(2026, Month.JULY)

        val nextBuffer = calendarLogic.getNextMonthBufferDays(jul2026)

        assertEquals(9, nextBuffer.size)
        // Der letzte Puffertag muss der 9. August sein
        assertEquals(9, nextBuffer.last().dayOfMonth)
        assertEquals(Month.AUGUST.value, nextBuffer.last().monthValue)
    }

    // Hinweis: Ein typischer Kalender zeigt ein 6-Wochen-Grid mit insgesamt 42 Tagen.
    @Test
    fun `Gesamtes Grid fuer Mai 2026 (31 Tage) hat exakt 42 Zellen`() {
        val may2026 = YearMonth.of(2026, 5)

        val startBuffer = calendarLogic.getPreviousMonthBufferDays(may2026)
        val mainDays = calendarLogic.getDaysInMonth(may2026)
        val endBuffer = calendarLogic.getNextMonthBufferDays(may2026)

        val totalGridSize = startBuffer.size + mainDays.size + endBuffer.size

        assertEquals(42, totalGridSize)
    }

    @Test
    fun `Gesamtes Grid fuer Feb 2026 (28 Tage) hat exakt 42 Zellen`() {
        val feb2026 = YearMonth.of(2026, Month.FEBRUARY)

        val startBuffer = calendarLogic.getPreviousMonthBufferDays(feb2026)
        val mainDays = calendarLogic.getDaysInMonth(feb2026)
        val endBuffer = calendarLogic.getNextMonthBufferDays(feb2026)

        val totalGridSize = startBuffer.size + mainDays.size + endBuffer.size

        assertEquals(42, totalGridSize)
    }
}