package com.example.gymify.core.presentation.navigation

sealed interface BottomNavAction {
    data object OnHomeClick : BottomNavAction
    data object OnAnalyticsClick : BottomNavAction
    data object OnSettingsClick : BottomNavAction
}