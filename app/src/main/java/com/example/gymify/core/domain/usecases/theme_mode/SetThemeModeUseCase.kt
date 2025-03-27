package com.example.gymify.core.domain.usecases.theme_mode

import com.example.gymify.core.domain.manager.AppCoreManager
import com.example.gymify.core.domain.model.ThemeMode

class SetThemeModeUseCase(
    private val appCoreManager: AppCoreManager
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        appCoreManager.setThemeMode(themeMode)
    }
}