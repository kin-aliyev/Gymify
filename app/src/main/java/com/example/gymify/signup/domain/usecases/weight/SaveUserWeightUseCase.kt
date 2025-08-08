package com.example.gymify.signup.domain.usecases.weight

import com.example.gymify.core.domain.manager.LocalUserInfoManager

class SaveUserWeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userWeight: Float) {
        localUserInfoManager.saveUserWeight(userWeight)
    }
}