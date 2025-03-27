package com.example.gymify.sign_up.domain.usecases.gender

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.sign_up.domain.model.UserGender

class SaveUserGenderUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userGender: UserGender) {
        localUserInfoManager.saveUserGender(userGender)
    }
}