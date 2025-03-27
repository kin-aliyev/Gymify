package com.example.gymify.sign_up.domain.usecases.height

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserHeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<Float> {
        return localUserInfoManager.readUserHeight()
    }
}