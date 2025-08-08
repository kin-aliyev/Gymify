package com.example.gymify.signup.presentation.pick_gender_screen

import com.example.gymify.core.domain.model.UserGender

data class PickGenderState(
    val selectedGender: UserGender? = null,
)