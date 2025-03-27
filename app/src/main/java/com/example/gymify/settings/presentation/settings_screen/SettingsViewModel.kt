package com.example.gymify.settings.presentation.settings_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
): ViewModel() {
    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    init {
        loadWeightUnit()
    }

    private fun loadWeightUnit() {
        viewModelScope.launch {
            val weightUnit = signUpUseCases.readUserWeightUnitUseCase().first()
            _state.value = _state.value.copy(weightUnit = weightUnit)
        }
    }

    fun onAction(action: SettingsAction) {
        return when (action) {
            SettingsAction.OnProfileEditClick -> TODO()
        }
    }
}