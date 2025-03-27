package com.example.gymify.sign_up.domain.usecases.level

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.ExpertiseLevel

class SaveUserExpertiseLevelUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(expertiseLevel: ExpertiseLevel) {
        localUserInfoManager.saveUserExpertiseLevel(expertiseLevel)
    }
}