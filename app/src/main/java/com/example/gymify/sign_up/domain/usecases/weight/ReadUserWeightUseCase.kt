package com.example.gymify.sign_up.domain.usecases.weight

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserWeightUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<Float> {
        return localUserInfoManager.readUserWeight()
    }
}