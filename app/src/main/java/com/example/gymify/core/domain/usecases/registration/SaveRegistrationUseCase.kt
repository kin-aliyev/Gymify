package com.example.gymify.core.domain.usecases.registration

import com.example.gymify.core.domain.manager.AppCoreManager

class SaveRegistrationUseCase(
    private val appCoreManager: AppCoreManager
) {
    suspend operator fun invoke(isRegistered: Boolean) {
        appCoreManager.saveRegistrationStatus(isRegistered)
    }
}