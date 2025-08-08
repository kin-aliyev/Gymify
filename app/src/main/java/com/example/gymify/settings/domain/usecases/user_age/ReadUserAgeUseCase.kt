package com.example.gymify.settings.domain.usecases.user_age

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserAgeUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<Int?> {
        return localUserInfoManager.readUserAge()
    }
}