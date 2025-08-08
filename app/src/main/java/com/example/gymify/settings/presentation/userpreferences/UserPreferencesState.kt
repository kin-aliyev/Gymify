package com.example.gymify.settings.presentation.userpreferences

import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit

data class UserPreferencesState(
    val profilePictureUri: String? = null,
    val userName: String? = "Gym Bro",
    val userGender: UserGender = UserGender.MALE,
    val userAge: Int? = null,
    val userHeightUnit: UserHeightUnit = UserHeightUnit.CM,
    val userHeight: Float? = null,
    val userWeightUnit: UserWeightUnit = UserWeightUnit.KG,
    val userWeight: Float? = null
)
