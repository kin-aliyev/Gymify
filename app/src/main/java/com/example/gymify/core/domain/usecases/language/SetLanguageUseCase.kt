package com.example.gymify.core.domain.usecases.language

import com.example.gymify.core.domain.manager.AppCoreManager
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(
    private val appCoreManager: AppCoreManager
) {
    suspend operator fun invoke(language: String) {
        appCoreManager.setLanguage(language)
    }
}