package com.example.gymify.settings.presentation.language_screen

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.data.manager.LocaleManager
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import com.example.gymify.core.domain.usecases.language.GetLanguageUseCase
import com.example.gymify.core.domain.usecases.language.SetLanguageUseCase
import com.example.gymify.settings.presentation.language_screen.components.LanguageTabItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val localeManager: LocaleManager
): ViewModel() {
    private val _state = MutableStateFlow(LanguageState())
    val state = _state.asStateFlow()

    init {
        loadSelectedLanguage()
        observeLanguageChanges()
    }

    fun onAction(action: LanguageAction) {
        when (action) {
            is LanguageAction.LanguageSelected -> setLanguage(action.language)
        }
    }

    private fun setLanguage(language: LanguageTabItem) {
        localeManager.changeLanguage(language.locale)
    }

    private fun loadSelectedLanguage() {
        viewModelScope.launch {
            val languageCode = getLanguageUseCase()
            updateSelectedLanguage(languageCode)
        }
    }

    private fun observeLanguageChanges() {
        viewModelScope.launch {
            getLanguageUseCase.observeLanguage().collect { languageCode ->
                updateSelectedLanguage(languageCode)
            }
        }
    }

    private fun updateSelectedLanguage(languageCode: String) {
        val selectedLanguage = LanguageTabItem.entries.find { it.locale == languageCode }
            ?: LanguageTabItem.English

        _state.update { it.copy(selectedLanguage = selectedLanguage) }
    }
}