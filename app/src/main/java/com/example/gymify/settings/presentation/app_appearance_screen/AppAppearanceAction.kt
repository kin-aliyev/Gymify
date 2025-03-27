package com.example.gymify.settings.presentation.app_appearance_screen

import com.example.gymify.core.domain.model.ThemeMode

sealed interface AppAppearanceAction {

    data object OpenThemeBottomSheet : AppAppearanceAction // Открытие bottom sheet для выбора темы

    data class ThemeModeSelected(val themeMode: ThemeMode) : AppAppearanceAction // Действие при выборе темы

    data object ConfirmThemeSelection : AppAppearanceAction // Подтверждение выбора темы
    data object CancelThemeSelection : AppAppearanceAction // Отмена выбора темы

}