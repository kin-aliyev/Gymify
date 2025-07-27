package com.example.gymify.core.util

import android.content.Context
import com.example.gymify.R
import com.example.gymify.core.domain.model.ThemeMode

fun ThemeMode.getLocalizedName(context: Context): String {
    return when(this) {
        ThemeMode.LIGHT -> context.getString(R.string.theme_light)
        ThemeMode.DARK -> context.getString(R.string.theme_dark)
        ThemeMode.SYSTEM -> context.getString(R.string.theme_system)
    }
}