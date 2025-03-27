package com.example.gymify.core.domain.usecases.language

import com.example.gymify.core.domain.manager.AppCoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val appCoreManager: AppCoreManager
) {
    suspend operator fun invoke(): String {
        return appCoreManager.getLanguage()
    }

    // Добавляем возможность наблюдать за изменениями язык
    fun observeLanguage(): Flow<String> {
        return appCoreManager.languageFlow
    }
}