package com.example.gymify.settings.presentation.language

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.data.manager.AppLocaleManager
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import com.example.gymify.settings.presentation.language.components.AppLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val appLocaleManager: AppLocaleManager,
    private val appCoreUseCases: AppCoreUseCases,
    @ApplicationContext private val context: Context,
): ViewModel() {

    private val selectedLanguage = MutableStateFlow<AppLanguage?>(null)

    private val currentLanguageFlow = appCoreUseCases.getLanguageUseCase().map { langCode ->
        AppLanguage.entries.firstOrNull { it.code == langCode} ?: AppLanguage.ENGLISH
    }

    val state: StateFlow<LanguageState> = combine(
        currentLanguageFlow,
        selectedLanguage
    ) { currentLanguage, selectedLanguage ->
        LanguageState(
            selectedLanguage = selectedLanguage ?: currentLanguage
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LanguageState()
    )

    fun onAction(action: LanguageAction) {
        when (action) {
            is LanguageAction.OnChangeLanguage -> {
                viewModelScope.launch {
                    appCoreUseCases.setLanguageUseCase(action.language.code)
                    appLocaleManager.changeLanguage(context, action.language.code)
                    selectedLanguage.value = action.language
                }
            }

            LanguageAction.OnNavigateBack -> Unit
        }
    }

}