package com.example.gymify.sign_up.presentation.pick_gender_screen

import com.example.gymify.sign_up.domain.model.UserGender

sealed interface PickGenderAction {

    data class onGenderClick(val userGender: UserGender): PickGenderAction

    object SaveGender: PickGenderAction
}