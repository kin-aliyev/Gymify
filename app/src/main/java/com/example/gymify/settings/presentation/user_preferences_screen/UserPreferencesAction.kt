package com.example.gymify.settings.presentation.user_preferences_screen

import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.sign_up.domain.model.UserGender

sealed interface UserPreferencesAction {

    data object ShowGenderSelectionDialog : UserPreferencesAction
    data class UpdateGender(val gender: UserGender) : UserPreferencesAction

    data object ShowAgeSelectionDialog : UserPreferencesAction
    data class UpdateAge(val age: Int) : UserPreferencesAction

    data object ShowHeightSelectionDialog : UserPreferencesAction
    data class UpdateHeight(val height: String) : UserPreferencesAction

    data object ShowWeightSelectionDialog : UserPreferencesAction
    data class UpdateWeight(val weight: String) : UserPreferencesAction

    data object ShowWeightUnitSelectionDialog : UserPreferencesAction
    data class UpdateWeightUnit(val weightUnit: UserWeightUnit) : UserPreferencesAction

    data object ShowHeightUnitSelectionDialog : UserPreferencesAction
    data class UpdateHeightUnit(val heightUnit: UserHeightUnit) : UserPreferencesAction

}