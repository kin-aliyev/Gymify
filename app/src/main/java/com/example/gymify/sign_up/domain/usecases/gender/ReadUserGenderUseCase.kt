package com.example.gymify.sign_up.domain.usecases.gender

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.sign_up.domain.model.UserGender
import kotlinx.coroutines.flow.Flow

class ReadUserGenderUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<UserGender> {
        return localUserInfoManager.readUserGender()
    }
}