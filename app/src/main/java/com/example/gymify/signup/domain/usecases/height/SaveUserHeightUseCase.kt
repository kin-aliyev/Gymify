package com.example.gymify.signup.domain.usecases.height

import com.example.gymify.core.domain.manager.LocalUserInfoManager

class SaveUserHeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userHeight: Float) {
        localUserInfoManager.saveUserHeight(userHeight)
    }
}