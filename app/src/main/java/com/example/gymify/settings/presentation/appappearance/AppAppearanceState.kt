package com.example.gymify.settings.presentation.appappearance

import com.example.gymify.core.domain.model.ThemeMode
import com.example.gymify.settings.presentation.language.components.AppLanguage

data class AppAppearanceState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val selectedLanguage: AppLanguage = AppLanguage.ENGLISH,
    val isDarkTheme: Boolean = true
)
