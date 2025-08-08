package com.example.gymify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import com.example.gymify.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appCoreUseCases: AppCoreUseCases,
) : ViewModel() {

    val state = combine(
        appCoreUseCases.readRegistrationStatusUseCase(),
        appCoreUseCases.getThemeModeUseCase()
    ) { isRegistered, themeMode ->
        MainState(
            isRegistered = isRegistered,
            themeMode = themeMode,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MainState(isLoading = true)
    )

}