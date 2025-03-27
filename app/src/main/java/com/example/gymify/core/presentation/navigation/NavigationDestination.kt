package com.example.gymify.core.presentation.navigation

interface NavigationDestination {
    val section: BottomNavSection
}

enum class BottomNavSection {
    HOME,
    ANALYTICS,
    SETTINGS
}