package com.example.gymify.signup.domain.usecases.height

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserHeightUnit

class SaveUserHeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userHeightUnit: UserHeightUnit) {
        localUserInfoManager.saveUserHeightUnit(userHeightUnit)
    }
}