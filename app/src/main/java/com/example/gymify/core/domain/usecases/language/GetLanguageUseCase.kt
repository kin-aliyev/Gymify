package com.example.gymify.core.domain.usecases.language

import com.example.gymify.core.domain.manager.AppCoreManager
import kotlinx.coroutines.flow.Flow

class GetLanguageUseCase(
    private val appCoreManager: AppCoreManager
) {
    operator fun invoke(): Flow<String> {
        return appCoreManager.languageFlow
    }
}