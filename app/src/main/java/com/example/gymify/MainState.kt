package com.example.gymify

import com.example.gymify.core.domain.model.ThemeMode

data class MainState(
    val isRegistered: Boolean = false,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isLoading: Boolean = true
)