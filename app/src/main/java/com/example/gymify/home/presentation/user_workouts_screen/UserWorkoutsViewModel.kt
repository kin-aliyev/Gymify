package com.example.gymify.home.presentation.user_workouts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWorkoutsViewModel @Inject constructor(
    private val workoutPlanUseCases: WorkoutPlanUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(UserWorkoutsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val workoutPlans = workoutPlanUseCases.getAllWorkoutPlansUseCase()
            _state.update { it.copy(userWorkoutPlans = workoutPlans) }
        }
    }

}