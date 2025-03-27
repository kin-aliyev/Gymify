package com.example.gymify.sign_up.domain.usecases.weight

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserWeightUnit

class SaveUserWeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun  invoke(userWeightUnit: UserWeightUnit) {
        localUserInfoManager.saveUserWeightUnit(userWeightUnit)
    }
}