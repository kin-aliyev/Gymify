package com.example.gymify.settings.presentation.app_appearance_screen

import com.example.gymify.core.domain.model.ThemeMode

data class AppAppearanceState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val temporaryThemeMode: ThemeMode? = null,
    val isThemeBottomSheetOpen: Boolean = false,
    val isDarkTheme: Boolean = true
)
