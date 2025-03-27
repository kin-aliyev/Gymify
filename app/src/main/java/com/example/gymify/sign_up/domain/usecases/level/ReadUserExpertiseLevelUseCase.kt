package com.example.gymify.sign_up.domain.usecases.level

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.ExpertiseLevel
import kotlinx.coroutines.flow.Flow

class ReadUserExpertiseLevelUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<ExpertiseLevel> {
        return localUserInfoManager.readUserExpertiseLevel()
    }
}