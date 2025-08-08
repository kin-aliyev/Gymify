package com.example.gymify.settings.presentation.userpreferences

import android.net.Uri
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit

sealed interface UserPreferencesAction {
    object NavigateBack : UserPreferencesAction

    data class GetNewProfilePictureUri(val uri: Uri) : UserPreferencesAction
    data class OnChangeUserName(val name: String) : UserPreferencesAction

    data class OnChangeUserGender(val userGender: UserGender) : UserPreferencesAction
    data class OnChangeUserAge(val userAge: Int) : UserPreferencesAction
    data class OnChangeUserHeight(val userHeight: Float) : UserPreferencesAction
    data class OnChangeUserHeightUnit(val userHeightUnit: UserHeightUnit) : UserPreferencesAction
    data class OnChangeUserWeight(val userWeight: Float) : UserPreferencesAction
    data class OnChangeUserWeightUnit(val userWeightUnit: UserWeightUnit) : UserPreferencesAction
}