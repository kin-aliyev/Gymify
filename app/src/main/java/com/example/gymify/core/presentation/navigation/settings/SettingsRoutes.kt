package com.example.gymify.core.presentation.navigation.settings

import com.example.gymify.core.presentation.navigation.BottomNavSection
import com.example.gymify.core.presentation.navigation.NavigationDestination
import kotlinx.serialization.Serializable

@Serializable
object Settings : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.SETTINGS
}

@Serializable
object UserPreferences : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.SETTINGS

}

@Serializable
object AppAppearance : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.SETTINGS
}

@Serializable
object HelpAndSupport : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.SETTINGS
}

@Serializable
object Language :  NavigationDestination {
    override val section : BottomNavSection = BottomNavSection.SETTINGS
}

