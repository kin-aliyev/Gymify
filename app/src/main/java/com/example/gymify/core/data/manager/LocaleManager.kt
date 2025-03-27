package com.example.gymify.core.data.manager

import com.example.gymify.core.domain.usecases.language.GetLanguageUseCase
import com.example.gymify.core.domain.usecases.language.SetLanguageUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleManager @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    // Publicly expose the current language code for easy access
    private val _currentLanguage = MutableStateFlow("")
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    // A flow for activity recreation events
    private val _recreateActivities = MutableStateFlow(false)
    val recreateActivities: StateFlow<Boolean> = _recreateActivities.asStateFlow()

    init {
        observeLanguageChanges()
        initializeLocale()
    }

    fun changeLanguage(languageCode: String) {
        scope.launch {
            setLanguageUseCase(languageCode)
            _recreateActivities.value = true
        }
    }

    // Call this after activities have been recreated
    fun resetRecreationFlag() {
        _recreateActivities.value = false

    }

    private fun initializeLocale() {
        scope.launch {
            val languageCode = getLanguageUseCase()
            _currentLanguage.value = languageCode
        }
    }

    private fun observeLanguageChanges() {
        scope.launch {
            getLanguageUseCase.observeLanguage().collect { languageCode ->
                _currentLanguage.value = languageCode
                _recreateActivities.value = true
            }
        }
    }

}