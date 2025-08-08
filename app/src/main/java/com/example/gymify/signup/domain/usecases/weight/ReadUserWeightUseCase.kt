package com.example.gymify.signup.domain.usecases.weight

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserWeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<Float> {
        return localUserInfoManager.readUserWeight()
    }
}