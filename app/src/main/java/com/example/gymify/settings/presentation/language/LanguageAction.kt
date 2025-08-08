package com.example.gymify.settings.presentation.language

import com.example.gymify.settings.presentation.language.components.AppLanguage

sealed interface LanguageAction {
    object OnNavigateBack : LanguageAction

    data class OnChangeLanguage(val language: AppLanguage): LanguageAction
}