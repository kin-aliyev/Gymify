package com.example.gymify.home.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.data.repository.util.CurrentWorkoutRepository
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import com.example.gymify.signup.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentWorkoutRepository: CurrentWorkoutRepository,
    private val workoutPlanUseCases: WorkoutPlanUseCases,
    private val signUpUseCases: SignUpUseCases,
): ViewModel() {

    val state = combine(
        workoutPlanUseCases.getAllWorkoutPlansUseCase(),
        signUpUseCases.readUserExpertiseLevelUseCase()
    ) { userWorkoutPlans, expertiseLevel ->
        HomeState(userWorkoutPlans = userWorkoutPlans, userExpertiseLevel = expertiseLevel)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HomeState()
    )


    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.OnAddNewWorkoutClick -> {
                currentWorkoutRepository.setCurrentWorkoutId(null)
            }
            is HomeAction.OnWorkoutPlanClick -> Unit

            is HomeAction.OnPredefinedWorkoutPlanClick -> Unit
        }
    }
}