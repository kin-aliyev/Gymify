package com.example.gymify.sign_up.domain.usecases.weight

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager

class SaveUserWeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userWeight: Float) {
        localUserInfoManager.saveUserWeight(userWeight)
    }
}