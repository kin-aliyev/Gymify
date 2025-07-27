package com.example.gymify.home.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.data.repository.util.CurrentWorkoutRepository
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentWorkoutRepository: CurrentWorkoutRepository,
    private val workoutPlanUseCases: WorkoutPlanUseCases
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        loadUserWorkoutPlans()
    }

    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.OnAddNewWorkoutClick -> {
                currentWorkoutRepository.setCurrentWorkoutId(null)
            }

            is HomeAction.OnWorkoutPlanClick -> {
//                currentWorkoutRepository.setCurrentWorkoutId(action.workoutPlan.id)
            //   TODO скорее всего это придется убрать ведь при нажатии перейдет на другой экран
            }
        }
    }

    private fun loadUserWorkoutPlans() {
        viewModelScope.launch {
            val userWorkoutPlans = workoutPlanUseCases.getUserDefinedWorkoutPlansUseCase()
            _state.value = _state.value.copy(
                userWorkoutPlans = userWorkoutPlans
            )
        }
    }
}