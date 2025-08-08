package com.example.gymify.signup.presentation.pick_gender_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.signup.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickGenderViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
): ViewModel() {
    private val _state = mutableStateOf(PickGenderState())
    val state: State<PickGenderState> = _state

    fun onAction(action: PickGenderAction) {
        return when (action) {
            //Check click to the Gender Item
            is PickGenderAction.onGenderClick -> {
                selectGender(action.userGender)
            }
            // Saving Gender to DataStore
            is PickGenderAction.SaveGender -> {
                saveGender()
            }
        }
    }

    private fun selectGender(userGender: UserGender) {
        _state.value = _state.value.copy(selectedGender = userGender)
    }

    private fun saveGender() {
        viewModelScope.launch {
            _state.value.selectedGender?.let {
                signUpUseCases.saveUserGenderUseCase(it)
            }
        }
    }

}