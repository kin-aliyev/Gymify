package com.example.gymify.signup.presentation.pick_gender_screen

import com.example.gymify.core.domain.model.UserGender

sealed interface PickGenderAction {

    data class onGenderClick(val userGender: UserGender): PickGenderAction

    object SaveGender: PickGenderAction
}