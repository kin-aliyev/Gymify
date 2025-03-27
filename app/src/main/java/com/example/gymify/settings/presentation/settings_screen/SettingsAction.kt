package com.example.gymify.settings.presentation.settings_screen

sealed interface SettingsAction {

    // Profile actions
    data object OnProfileEditClick : SettingsAction

// Navigation bar actions
//    data class Navigation(val action: BottomNavAction) : SettingsAction

}