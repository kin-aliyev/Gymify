package com.example.gymify.core.presentation.navigation.analytics

import com.example.gymify.core.presentation.navigation.BottomNavSection
import com.example.gymify.core.presentation.navigation.NavigationDestination
import kotlinx.serialization.Serializable

@Serializable
object Analytics : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.ANALYTICS
}