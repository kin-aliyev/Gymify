package com.example.gymify.signup.domain.usecases.gender

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserGender
import kotlinx.coroutines.flow.Flow

class ReadUserGenderUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<UserGender> {
        return localUserInfoManager.readUserGender()
    }
}