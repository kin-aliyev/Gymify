package com.example.gymify.settings.domain.usecases.user_name

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserNameUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<String?> {
        return localUserInfoManager.readUserName()
    }
}