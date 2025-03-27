package com.example.gymify.settings.presentation.settings_screen

import com.example.gymify.core.domain.model.UserWeightUnit

data class SettingsState(
    // User Info
    val userName: String = "",
    val profilePictureUrl: String? = null,

    val squatMaxWeight: String = "",
    val deadliftMaxWeight: String = "",
    val benchMaxWeight: String = "",

    // User preferences
    val weightUnit: UserWeightUnit = UserWeightUnit.KG
    
)
