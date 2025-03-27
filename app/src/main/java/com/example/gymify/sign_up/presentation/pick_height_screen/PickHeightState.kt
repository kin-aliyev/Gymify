package com.example.gymify.sign_up.presentation.pick_height_screen

import com.example.gymify.core.domain.model.UserHeightUnit

data class PickHeightState (
    val selectedHeightUnit: UserHeightUnit = UserHeightUnit.CM, // Measurement Unit (CM or FT)
    val selectedHeight: Float = 170f // Selected User Height
)