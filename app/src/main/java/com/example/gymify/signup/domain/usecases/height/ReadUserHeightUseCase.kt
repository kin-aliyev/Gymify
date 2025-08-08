package com.example.gymify.signup.domain.usecases.height

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserHeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<Float> {
        return localUserInfoManager.readUserHeight()
    }
}