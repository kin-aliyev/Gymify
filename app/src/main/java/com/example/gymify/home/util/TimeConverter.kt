package com.example.gymify.home.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TimeConverter {

    fun convertLongToDate(
        timeStamp: Long,
        pattern: String = "d MMMM yyyy",
        locale: Locale = Locale.getDefault(),
    ): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            convertWithNewApi(timeStamp, pattern, locale)
        } else {
            convertWithLegacyApi(timeStamp, pattern, locale)
        }
    }

    // Современный API для (Android 8.0 +)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertWithNewApi(
        timeStamp: Long,
        pattern: String,
        locale: Locale,
    ): String {
        // Шаг 1. Instant - это "момент времени" в UTC без учета временной зоны
        val instant = Instant.ofEpochMilli(timeStamp)

        /* Шаг 2. ZoneId.systemDefault() - Это временная зона устройства пользователя (Europe/Moscow", "Asia/Tokyo")
         Система автоматически получает эту зону из настроек устройства и показывает локальное время */
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

        // Шаг 3. Создаем форматтер с паттерном и локалью
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)

        // Применяем форматирование
        return localDateTime.format(formatter)
    }

    private fun convertWithLegacyApi(
        timeStamp: Long,
        pattern: String,
        locale: Locale,
    ): String {
        // Создаем SimpleDateFormat с паттерном и локалью
        val formatter = SimpleDateFormat(pattern, locale)

        // Устанавливаем временную зону TimeZone.getDefault() - это аналог ZoneId.systemDefault() для старого API
        formatter.timeZone = TimeZone.getDefault()

        // Создаем Date из timestamp и форматируем
        return formatter.format(Date(timeStamp))
    }

    object Patterns {
        const val WEEKDAY_ANALYTICS_DATE = "EEEE, d MMMM" // понедельник, 7 июля
        const val MONTH_DATE = "MMMM, yyyy"               // Июль, 2025
        const val FULL_DATE = "d MMMM yyyy"               // 7 июля 2025
        const val US_FORMAT = "MMMM d, yyyy"              // July 7, 2025
        const val SHORT_DATE = "dd.MM.yyyy"               // 07.07.2025
        const val DATE_TIME = "d MMMM yyyy, HH:mm"        // 7 июля 2025, 14:30
        const val SHORT_DATE_TIME = "EEE, d MMM yyyy, HH:mm"  // пн, 7 июля 2025, 14:30
        const val DATE_TIME_SECONDS = "d MMMM yyyy, HH:mm:ss" // 7 июля 2025, 14:30:45
        const val TIME_ONLY = "HH:mm"                     // 14:30
        const val TIME_WITH_SECONDS = "HH:mm:ss"          // 14:30:45
        const val DAY_MONTH = "d MMMM"                    // 7 июля
        const val WEEKDAY_DATE = "EEEE, d MMMM yyyy"      // понедельник, 7 июля 2025
        const val WEEKDAY_SHORT_DATE = "EEE, dd.MM.yyyy"  // пн, 07.07.2025
        const val ISO_DATE = "yyyy-MM-dd"                 // 2025-07-07
    }
}