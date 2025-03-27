package com.example.gymify.sign_up.domain.usecases.height

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.UserHeightUnit

class SaveUserHeightUnitUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(userHeightUnit: UserHeightUnit) {
        localUserInfoManager.saveUserHeightUnit(userHeightUnit)
    }
}