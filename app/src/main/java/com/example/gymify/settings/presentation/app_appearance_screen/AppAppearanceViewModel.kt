package com.example.gymify.settings.presentation.app_appearance_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppAppearanceViewModel @Inject constructor(
    private val appCoreUseCases: AppCoreUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AppAppearanceState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            appCoreUseCases.getThemeModeUseCase().collect { themeMode ->
                _state.update { it.copy(themeMode = themeMode) }
            }
        }
    }

    fun onAction(action: AppAppearanceAction) {
        when (action) {

            is AppAppearanceAction.CancelThemeSelection -> {
                _state.update { it.copy(isThemeBottomSheetOpen = false, temporaryThemeMode = null) }
            }

            is AppAppearanceAction.ConfirmThemeSelection -> {
                val tempTheme = _state.value.temporaryThemeMode
                if (tempTheme != null) {
                    viewModelScope.launch {
                        appCoreUseCases.setThemeModeUseCase(tempTheme)
                        _state.update {
                            it.copy(
                                themeMode = tempTheme,
                                isThemeBottomSheetOpen = false,
                                temporaryThemeMode = null
                            )
                        }
                    }
                }

            }

            is AppAppearanceAction.OpenThemeBottomSheet -> {
                _state.update { it.copy(isThemeBottomSheetOpen = true, temporaryThemeMode = _state.value.themeMode) }
            }

            is AppAppearanceAction.ThemeModeSelected -> {
                _state.update { it.copy(temporaryThemeMode = action.themeMode) }
            }
        }
    }

}