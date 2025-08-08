package com.example.gymify.signup.domain.usecases.height

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserHeightUnit
import kotlinx.coroutines.flow.Flow

class ReadUserHeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<UserHeightUnit> {
        return localUserInfoManager.readUserHeightUnit()
    }
}