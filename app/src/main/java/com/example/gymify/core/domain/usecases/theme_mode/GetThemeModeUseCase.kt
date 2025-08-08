package com.example.gymify.core.domain.usecases.theme_mode

import com.example.gymify.core.domain.manager.AppCoreManager
import com.example.gymify.core.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

class GetThemeModeUseCase(
    private val appCoreManager: AppCoreManager
) {
    operator fun invoke(): Flow<ThemeMode> {
        return appCoreManager.themeModeFlow
    }
}