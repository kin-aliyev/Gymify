package com.example.gymify

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.model.ThemeMode
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import com.example.gymify.core.presentation.navigation.MainAction
import com.example.gymify.core.presentation.navigation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appCoreUseCases: AppCoreUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    val themeMode: Flow<ThemeMode> = appCoreUseCases.getThemeModeUseCase()

    init {
        readRegistrationStatus()
    }


    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.ReadRegistrationStatus -> readRegistrationStatus()
        }
    }

    private fun readRegistrationStatus() {
        viewModelScope.launch {
            appCoreUseCases.readRegistrationStatusUseCase().collect { isRegistered ->
                Log.d("MainViewModel", "Registration status updated: $isRegistered")
                _state.value = _state.value.copy(isRegistered = isRegistered, isLoading = false)
            }
        }
    }
}