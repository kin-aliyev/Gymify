package com.example.gymify.home.presentation.workout_complete_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WorkoutCompleteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(WorkoutCompleteState())
    val state = _state.asStateFlow()

    init {
        val workoutTimeSeconds = savedStateHandle.get<Int>("timeElapsedSeconds")
        _state.update { it.copy(workoutTimeSeconds = workoutTimeSeconds ?: 0, isActiveAnimation = true) }
    }

    fun onAction(action: WorkoutCompleteAction) {

    }
}