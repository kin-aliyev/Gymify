package com.example.gymify.sign_up.presentation.pick_weight_screen

import com.example.gymify.core.domain.model.UserWeightUnit

data class PickWeightState(
    val selectedWeightUnit: UserWeightUnit = UserWeightUnit.KG, // Measurement Unit (KG or LBS)
    val selectedWeight: Float = 60f // Selected User Weight
)