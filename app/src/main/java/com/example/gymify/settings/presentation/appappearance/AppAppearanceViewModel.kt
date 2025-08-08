package com.example.gymify.settings.presentation.appappearance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import com.example.gymify.settings.presentation.language.components.AppLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppAppearanceViewModel @Inject constructor(
    private val appCoreUseCases: AppCoreUseCases,
) : ViewModel() {

    val state = combine(
        appCoreUseCases.getThemeModeUseCase(),
        appCoreUseCases.getLanguageUseCase()
    ) { themeMode, selectedLanguage ->
        AppAppearanceState(
            themeMode = themeMode,
            selectedLanguage = AppLanguage.fromCode(selectedLanguage)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AppAppearanceState()
    )

    fun onAction(action: AppAppearanceAction) {
        when (action) {
            is AppAppearanceAction.OnThemeModeChange -> {
                viewModelScope.launch {
                    appCoreUseCases.setThemeModeUseCase(action.themeMode)
                }
            }

            AppAppearanceAction.OnNavigateBack -> Unit
            AppAppearanceAction.OnNavigateToLanguageSettings -> Unit
        }
    }

}