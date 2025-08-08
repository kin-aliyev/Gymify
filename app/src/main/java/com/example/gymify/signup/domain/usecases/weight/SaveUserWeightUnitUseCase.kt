package com.example.gymify.signup.domain.usecases.weight

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserWeightUnit

class SaveUserWeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun  invoke(userWeightUnit: UserWeightUnit) {
        localUserInfoManager.saveUserWeightUnit(userWeightUnit)
    }
}