package com.example.gymify.core.domain.usecases

import com.example.gymify.core.domain.usecases.language.GetLanguageUseCase
import com.example.gymify.core.domain.usecases.language.SetLanguageUseCase
import com.example.gymify.core.domain.usecases.registration.ReadRegistrationStatusUseCase
import com.example.gymify.core.domain.usecases.registration.SaveRegistrationUseCase
import com.example.gymify.core.domain.usecases.theme_mode.GetThemeModeUseCase
import com.example.gymify.core.domain.usecases.theme_mode.SetThemeModeUseCase

data class AppCoreUseCases (

    val saveRegistrationUseCase: SaveRegistrationUseCase,
    val readRegistrationStatusUseCase: ReadRegistrationStatusUseCase,

    val getThemeModeUseCase: GetThemeModeUseCase,
    val setThemeModeUseCase: SetThemeModeUseCase,

    val getLanguageUseCase: GetLanguageUseCase,
    val setLanguageUseCase: SetLanguageUseCase

)