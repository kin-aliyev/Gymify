package com.example.gymify.core.domain.usecases.registration

import com.example.gymify.core.domain.manager.AppCoreManager
import kotlinx.coroutines.flow.Flow

class ReadRegistrationStatusUseCase(
    private val appCoreManager: AppCoreManager
) {
    operator fun invoke(): Flow<Boolean> {
        return appCoreManager.readRegistrationStatus()
    }
}