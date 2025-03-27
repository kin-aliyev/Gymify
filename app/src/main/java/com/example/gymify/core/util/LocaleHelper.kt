package com.example.gymify.core.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode) // Создаем локаль из кода языка
        Locale.setDefault(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.create(locale)
            )
            context
        } else {
            val config = Configuration(context.resources.configuration) // Получаем текущую конфигурацию
            config.setLocales(android.os.LocaleList(locale))
            context.createConfigurationContext(config) // Return a new context with the updated configuration
        }

    }
}