package com.example.gymify.sign_up.presentation.pick_height_screen

import com.example.gymify.core.domain.model.UserHeightUnit

sealed interface PickHeightAction {

    data class onHeightUnitClick(val userHeightUnit: UserHeightUnit): PickHeightAction
    object SaveHeightUnit: PickHeightAction

    data class onHeightChange(val height: Float): PickHeightAction
    object SaveHeight: PickHeightAction
}