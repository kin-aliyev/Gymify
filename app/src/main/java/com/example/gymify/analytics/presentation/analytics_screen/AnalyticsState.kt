package com.example.gymify.analytics.presentation.analytics_screen

import com.example.gymify.home.domain.model.TimeScale
import com.example.gymify.analytics.presentation.analytics_screen.components.BarData

data class AnalyticsState(
    val data: List<BarData> = emptyList(),
    val isBarSelected: Boolean = false,
    val selectedScale: TimeScale = TimeScale.DAY,
    val selectedIndex: Int? = null,
    val selectedBarDurationMinutes: Int? = null,
    val selectedDateTitle: String? = null,
    val totalTimeMinutes: Int = 0,
    val bmiValue: Float = 0f,

)
