package com.example.gymify.signup.domain.usecases.level

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.ExpertiseLevel

class SaveUserExpertiseLevelUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(expertiseLevel: ExpertiseLevel) {
        localUserInfoManager.saveUserExpertiseLevel(expertiseLevel)
    }
}