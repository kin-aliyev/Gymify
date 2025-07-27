package com.example.gymify.home.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.gymify.home.domain.model.WorkoutSession
import com.example.gymify.home.presentation.analytics_screen.components.BarData
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

class TimeRangeUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeekRangeFromTimestamp(
        timestamp: Long = System.currentTimeMillis(),
        locale: Locale = Locale.getDefault(),
        zoneId: ZoneId = ZoneId.systemDefault(),
    ): Pair<Long, Long> {

        val date = Instant.ofEpochMilli(timestamp)
            .atZone(zoneId)
            .toLocalDate()

        // Получаем локальные настройки недели
        val weekFields = WeekFields.of(locale)

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapSessionsToDayBarData(
        sessions: List<WorkoutSession>,
        locale: Locale = Locale.getDefault(),
        zoneId: ZoneId = ZoneId.systemDefault(),
    ): List<BarData> {
        val sessionsGroupedByDay = sessions.groupBy { session ->
            Instant.ofEpochMilli(session.timestamp)
                .atZone(zoneId)
                .toLocalDate()
                .dayOfWeek
        }

        return DayOfWeek.entries.map { day ->
            val totalSeconds = sessionsGroupedByDay[day]?.sumOf { it.durationSeconds } ?: 0L
            val label = day.getDisplayName(TextStyle.SHORT, locale) // 'Mon' 'Tue'
            Log.d("TimeRangeUtil", "total seconds $totalSeconds")

            BarData(
                minutes = totalSeconds.toInt() * 10,
                label = label,
                timestamp = sessionsGroupedByDay[day]?.maxByOrNull { it.timestamp }?.timestamp
            )
        }
    }
}