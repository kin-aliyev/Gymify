package com.example.gymify.settings.domain.usecases.user_name

import com.example.gymify.core.domain.manager.LocalUserInfoManager

class SaveUserNameUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(name: String) {
        localUserInfoManager.saveUserName(name)
    }
}