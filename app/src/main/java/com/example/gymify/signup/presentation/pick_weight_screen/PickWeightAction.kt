package com.example.gymify.signup.presentation.pick_weight_screen

import com.example.gymify.core.domain.model.UserWeightUnit

sealed interface PickWeightAction {

    data class onWeightUnitClick(val userWeightUnit: UserWeightUnit): PickWeightAction
    object SaveWeightUnit: PickWeightAction

    data class onWeightChange(val weight: Float): PickWeightAction
    object SaveWeight: PickWeightAction
}