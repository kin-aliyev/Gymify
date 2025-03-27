package com.example.gymify.settings.presentation.user_preferences_screen

import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.sign_up.domain.model.UserGender

data class UserPreferencesState(
    val userGender: UserGender,
    val userAge: Int,
    val userHeight: String,
    val userWeight: String,
    val userWeightUnit: UserWeightUnit,
    val userHeightUnit: UserHeightUnit
)
