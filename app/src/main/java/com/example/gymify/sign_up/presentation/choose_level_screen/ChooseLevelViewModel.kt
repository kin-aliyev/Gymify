package com.example.gymify.sign_up.presentation.choose_level_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.manager.AppCoreManager
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseLevelViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases,
    private val appCoreManager: AppCoreManager
): ViewModel() {
    private val _state = mutableStateOf(ChooseLevelState())
    val state: State<ChooseLevelState> = _state

    fun onAction(action: ChooseLevelAction) {
        when (action) {
            is ChooseLevelAction.onExpertiseLevelClick -> onExpertiseLevelSelected(action.expertiseLevel)

            is ChooseLevelAction.SaveExpertiseLevel -> saveExpertiseLevel()

        }
    }

    private fun onExpertiseLevelSelected(expertiseLevel: ExpertiseLevel) {
        _state.value = _state.value.copy( selectedExpertiseLevel = expertiseLevel )
    }

    private fun saveExpertiseLevel() {
        viewModelScope.launch {
            _state.value.selectedExpertiseLevel.let {
                signUpUseCases.saveUserExpertiseLevelUseCase(it)

                appCoreManager.saveRegistrationStatus(true)
            }
        }
    }


}
