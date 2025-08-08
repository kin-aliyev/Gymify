package com.example.gymify.home.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gymify.home.domain.model.WorkoutSession
import com.example.gymify.analytics.presentation.analytics_screen.components.BarData
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class TimeRangeUtils {
    private val sharedZoneId: ZoneId = ZoneId.systemDefault()
    private val sharedLocale: Locale = Locale.getDefault()

    // Для TimeScale.Day
    fun getWeekRangeFromTimestamp(
        timestamp: Long = System.currentTimeMillis(),
        zoneId: ZoneId = sharedZoneId,
    ): Pair<Long, Long> {

        val date = Instant.ofEpochMilli(timestamp)
            .atZone(zoneId)
            .toLocalDate()

        val startOfWeek = date
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        val endOfWeekExclusive = date
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            .plusDays(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        return startOfWeek to endOfWeekExclusive
    }

    fun mapSessionsToDayBarData(
        sessions: List<WorkoutSession>,
        locale: Locale = sharedLocale,
        zoneId: ZoneId = sharedZoneId,
    ): List<BarData> {
        val sessionsGroupedByDay = sessions.groupBy { session ->
            Instant.ofEpochMilli(session.timestamp)
                .atZone(zoneId)
                .toLocalDate()
                .dayOfWeek
        }

        val (startOfTheWeek, _) = getWeekRangeFromTimestamp()
        val startOfTheWeekDate = Instant.ofEpochMilli(startOfTheWeek)
            .atZone(zoneId)
            .toLocalDate()

        return DayOfWeek.entries.map { day ->
            val totalSeconds = sessionsGroupedByDay[day]?.sumOf { it.durationSeconds } ?: 0L
            val label = day.getDisplayName(TextStyle.SHORT, locale) // 'Mon' 'Tue'

            val dayTimeStamp = startOfTheWeekDate
                .with(TemporalAdjusters.nextOrSame(day))
                .atStartOfDay(zoneId)
                .toInstant()
                .toEpochMilli()

            BarData(
                minutes = totalSeconds.toInt(),
                label = label,
                timestamp = dayTimeStamp
            )
        }
    }

    // Для TimeScale.Week

    fun getFourWeeksRangeFromTimestamp(
        timestamp: Long = System.currentTimeMillis(),
        zoneId: ZoneId = sharedZoneId,
    ): Pair<Long, Long> {
        val currentDate = Instant.ofEpochMilli(timestamp)
            .atZone(zoneId)
            .toLocalDate()

        val currentWeekStart = currentDate
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        val fourWeeksStart = currentWeekStart
            .minusWeeks(3)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        val fourWeeksEnd = currentDate
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            .plusDays(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        return fourWeeksStart to fourWeeksEnd
    }

    fun getFourWeekRanges(
        timestamp: Long = System.currentTimeMillis(),
        zoneId: ZoneId = sharedZoneId,
    ): List<WeekRange> {
        val currentDate = Instant.ofEpochMilli(timestamp)
            .atZone(zoneId)
            .toLocalDate()

        val currentWeekStart = currentDate
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        return (3 downTo 0).map { weeksBack ->
            val weekStart = currentWeekStart.minusWeeks(weeksBack.toLong())
            val weekEnd = weekStart.plusDays(6)

            val startTimestamp = weekStart
                .atStartOfDay(zoneId)
                .toInstant()
                .toEpochMilli()

            val endTimestamp = weekEnd
                .plusDays(1)
                .atStartOfDay(zoneId)
                .toInstant()
                .toEpochMilli()

            val label = formatWeekRangeLabel(weekStart, weekEnd)

            WeekRange(
                startTimestamp = startTimestamp,
                endTimestamp = endTimestamp,
                weekStart = weekStart,
                weekEnd = weekEnd,
                label = label
            )
        }
    }

    private fun formatWeekRangeLabel(
        weekStart: LocalDate,
        weekEnd: LocalDate,
        locale: Locale = sharedLocale,
    ): String {
        val dayFormatter = DateTimeFormatter.ofPattern("d", locale)
        val monthFormatter = DateTimeFormatter.ofPattern("MMM", locale)

        val startDay = weekStart.format(dayFormatter)
        val startMonth = weekStart.format(monthFormatter)
        val endDay = weekEnd.format(dayFormatter)
        val endMonth = weekEnd.format(monthFormatter)

        return if (weekStart.month == weekEnd.month) {
            "$startDay - $endDay $endMonth"
        } else "$startDay $startMonth - $endDay $endMonth"
    }

    fun mapSessionsToWeekBarData(
        sessions: List<WorkoutSession>,
    ): List<BarData> {
        val weekRanges = getFourWeekRanges()
        val romanNumerals = listOf("I", "II", "III", "IV")

        return weekRanges.mapIndexed { index, weekRange ->
            val weekSessions = sessions.filter { session ->
                session.timestamp >= weekRange.startTimestamp &&
                        session.timestamp < weekRange.endTimestamp
            }

            val totalSeconds = weekSessions.sumOf { it.durationSeconds }

            BarData(
                minutes = totalSeconds.toInt(),
                label = romanNumerals[index],
                timestamp = weekRange.startTimestamp
            )
        }
    }

    fun getWeekHeaderTitle(
        weekIndex: Int,
        zoneId: ZoneId = sharedZoneId,
    ): String? {
        val weekRanges = getFourWeekRanges(zoneId = zoneId)
        return weekRanges.getOrNull(weekIndex)?.label
    }

    // Для TimeScale.Month

    fun getYearRangeFromTimestamp(
        timeStamp: Long = System.currentTimeMillis(),
        zoneId: ZoneId = sharedZoneId
    ): Pair<Long, Long> {
        val date = Instant.ofEpochMilli(timeStamp)
            .atZone(zoneId)
            .toLocalDate()

        val startOfTheYear = date
            .with(TemporalAdjusters.firstDayOfYear())
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        val endOfTheYear = date
            .with(TemporalAdjusters.lastDayOfYear())
            .plusDays(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        return startOfTheYear to endOfTheYear
    }

    fun mapSessionsToMonthBarData(
        sessions: List<WorkoutSession>,
        locale: Locale = sharedLocale,
        zoneId: ZoneId = sharedZoneId
    ): List<BarData> {
        val sessionsGroupedByMonth = sessions.groupBy { session ->
            Instant.ofEpochMilli(session.timestamp)
                .atZone(zoneId)
                .toLocalDate()
                .month
        }

        val currentYear = Instant.ofEpochMilli(System.currentTimeMillis())
            .atZone(zoneId)
            .toLocalDate()
            .year

        return Month.entries.map {month ->
            val totalSeconds = sessionsGroupedByMonth[month]?.sumOf { it.durationSeconds } ?: 0L
            val label = month.getDisplayName(TextStyle.SHORT, locale).first().toString()

            val monthTimestamp = LocalDate.of(currentYear, month, 1)
                .atStartOfDay(zoneId)
                .toInstant()
                .toEpochMilli()

            BarData(
                minutes = totalSeconds.toInt(),
                label = label,
                timestamp = monthTimestamp
            )
        }

    }
}

data class WeekRange(
    val startTimestamp: Long,
    val endTimestamp: Long, // Эксклюзивный конец
    val label: String,
    val weekStart: LocalDate,
    val weekEnd: LocalDate,
)