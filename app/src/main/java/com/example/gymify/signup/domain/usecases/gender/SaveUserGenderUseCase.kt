package com.example.gymify.signup.domain.usecases.gender

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserGender

class SaveUserGenderUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userGender: UserGender) {
        localUserInfoManager.saveUserGender(userGender)
    }
}