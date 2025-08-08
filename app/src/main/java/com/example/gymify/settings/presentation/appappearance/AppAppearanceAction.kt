package com.example.gymify.settings.presentation.appappearance

import com.example.gymify.core.domain.model.ThemeMode

sealed interface AppAppearanceAction {
    object OnNavigateBack : AppAppearanceAction

    data class OnThemeModeChange(val themeMode: ThemeMode) : AppAppearanceAction

    object OnNavigateToLanguageSettings : AppAppearanceAction
}