package com.example.gymify.settings.presentation.language_screen

import com.example.gymify.settings.presentation.language_screen.components.LanguageTabItem

sealed interface LanguageAction {
    data class LanguageSelected(val language: LanguageTabItem): LanguageAction
}