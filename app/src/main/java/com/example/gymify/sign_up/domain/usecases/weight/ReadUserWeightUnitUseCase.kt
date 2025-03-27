package com.example.gymify.sign_up.domain.usecases.weight

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserWeightUnit
import kotlinx.coroutines.flow.Flow

class ReadUserWeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<UserWeightUnit> {
        return localUserInfoManager.readUserWeightUnit()
    }
}