package com.example.gymify.analytics.presentation.analytics_screen

import com.example.gymify.home.domain.model.TimeScale

sealed interface AnalyticsAction {
    object LoadBarData : AnalyticsAction
    data class ChangeTimeScale(val timeScale: TimeScale) : AnalyticsAction
    data class OnBarClick(val index: Int) : AnalyticsAction
}