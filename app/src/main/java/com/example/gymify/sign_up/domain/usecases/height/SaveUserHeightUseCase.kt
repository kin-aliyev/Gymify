package com.example.gymify.sign_up.domain.usecases.height

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager

class SaveUserHeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userHeight: Float) {
        localUserInfoManager.saveUserHeight(userHeight)
    }
}