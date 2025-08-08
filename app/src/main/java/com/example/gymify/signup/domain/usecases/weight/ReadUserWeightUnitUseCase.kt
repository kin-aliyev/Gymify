package com.example.gymify.signup.domain.usecases.weight

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserWeightUnit
import kotlinx.coroutines.flow.Flow

class ReadUserWeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<UserWeightUnit> {
        return localUserInfoManager.readUserWeightUnit()
    }
}