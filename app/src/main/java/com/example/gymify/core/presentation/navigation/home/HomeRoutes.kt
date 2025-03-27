package com.example.gymify.core.presentation.navigation.home

import com.example.gymify.core.presentation.navigation.BottomNavSection
import com.example.gymify.core.presentation.navigation.NavigationDestination
import kotlinx.serialization.Serializable

@Serializable
object Home : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}