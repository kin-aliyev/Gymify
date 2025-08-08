package com.example.gymify.home.presentation.user_workouts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWorkoutsViewModel @Inject constructor(
    private val workoutPlanUseCases: WorkoutPlanUseCases
) : ViewModel() {

    val state = workoutPlanUseCases.getAllWorkoutPlansUseCase()
        .map { workoutPlans ->
            UserWorkoutsState(userWorkoutPlans =  workoutPlans)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UserWorkoutsState()
        )

}