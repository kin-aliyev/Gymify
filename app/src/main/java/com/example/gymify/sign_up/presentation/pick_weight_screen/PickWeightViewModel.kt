package com.example.gymify.sign_up.presentation.pick_weight_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickWeightViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
): ViewModel() {
    private val _state = mutableStateOf(PickWeightState())
    val state: State<PickWeightState> = _state

    fun onAction (action: PickWeightAction) {
        when (action) {

            is PickWeightAction.onWeightChange -> onWeightSelected(action.weight)
            is PickWeightAction.SaveWeight -> saveWeight()

            is PickWeightAction.onWeightUnitClick -> onWeightUnitClick(action.userWeightUnit)
            is PickWeightAction.SaveWeightUnit -> saveWeightUnit()
        }
    }

    private fun onWeightSelected(weight: Float) {
        _state.value = _state.value.copy( selectedWeight = weight )
    }

    private fun onWeightUnitClick(userWeightUnit: UserWeightUnit) {
        _state.value = _state.value.copy( selectedWeightUnit = userWeightUnit)
    }

    private fun saveWeight() {
        viewModelScope.launch {
            _state.value.selectedWeight.let {
                signUpUseCases.saveUserWeightUseCase(it)
            }
        }
    }

    private fun saveWeightUnit() {
        viewModelScope.launch {
            _state.value.selectedWeightUnit.let {
                signUpUseCases.saveUserWeightUnitUseCase
            }
        }
    }


}