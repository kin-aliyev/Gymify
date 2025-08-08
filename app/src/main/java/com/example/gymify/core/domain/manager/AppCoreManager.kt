package com.example.gymify.core.domain.manager

import com.example.gymify.core.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

interface AppCoreManager {
    // Registration status
    suspend fun saveRegistrationStatus(isRegistered: Boolean)
    fun readRegistrationStatus(): Flow<Boolean>

    // App Theme
    suspend fun setThemeMode(themeMode: ThemeMode)
    val themeModeFlow: Flow<ThemeMode>

    // App Language
    suspend fun setLanguage(language: String)
    val languageFlow: Flow<String>
}