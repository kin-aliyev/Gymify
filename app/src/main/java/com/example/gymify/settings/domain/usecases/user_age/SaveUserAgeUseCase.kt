package com.example.gymify.settings.domain.usecases.user_age

import com.example.gymify.core.domain.manager.LocalUserInfoManager

class SaveUserAgeUseCase(
    private val localUserInfoManager: LocalUserInfoManager,
) {
    suspend operator fun invoke(userAge: Int) {
        localUserInfoManager.saveUserAge(userAge)
    }
}