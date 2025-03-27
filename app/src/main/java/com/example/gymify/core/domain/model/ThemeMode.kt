package com.example.gymify.core.domain.model

enum class ThemeMode {
    LIGHT, DARK, SYSTEM;

    companion object {
        fun fromOrdinal(ordinal: Int): ThemeMode = values().getOrElse(ordinal) { SYSTEM }
    }
}