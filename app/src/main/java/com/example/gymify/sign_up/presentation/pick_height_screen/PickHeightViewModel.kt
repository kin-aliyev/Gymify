package com.example.gymify.sign_up.presentation.pick_height_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickHeightViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
): ViewModel() {
    private val _state = mutableStateOf(PickHeightState())
    val state: State<PickHeightState> = _state

    fun onAction(action: PickHeightAction) {
        when (action) {
            // Actions about Height value
            is PickHeightAction.onHeightChange -> onHeightSelected(action.height)
            is PickHeightAction.SaveHeight -> saveHeight()

            // Actions about Height Unit
            is PickHeightAction.onHeightUnitClick -> onHeightUnitSelected(action.userHeightUnit)
            is PickHeightAction.SaveHeightUnit -> saveHeightUnit()
        }
    }

    private fun onHeightSelected(height: Float) {
        _state.value = _state.value.copy( selectedHeight = height)
    }

    private fun onHeightUnitSelected(userHeightUnit: UserHeightUnit) {
        _state.value = _state.value.copy( selectedHeightUnit = userHeightUnit )
    }

    private fun saveHeight() {
        viewModelScope.launch {
            _state.value.selectedHeight.let {
                signUpUseCases.saveUserHeightUseCase(it)
            }
        }
    }

    private fun saveHeightUnit() {
        viewModelScope.launch {
            _state.value.selectedHeightUnit.let {
                signUpUseCases.saveUserHeightUnitUseCase(it)
            }
        }
    }

}