package com.example.gymify.home.presentation.workout_plan_detail_screen

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.data.local.predefined.PredefinedWorkoutExercises
import com.example.gymify.home.util.WorkoutPlanIconMapper
import com.example.gymify.home.util.getWorkoutPlanDisplayName
import com.example.gymify.home.data.repository.util.CurrentWorkoutRepository
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.home.domain.usecases.ExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class WorkoutPlanDetailViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val workoutPlanUseCases: WorkoutPlanUseCases,
    private val workoutExerciseUseCases: WorkoutExerciseUseCases,
    private val exerciseUseCases: ExerciseUseCases,
    private val signUpUseCases: SignUpUseCases,
    private val currentWorkoutRepository: CurrentWorkoutRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(WorkoutPlanDetailState())
    val state = _state.asStateFlow()

    // TODO ОБРАБОТКУ ONACTION И ДОБАВЛЕНИЕ В NAVIGATION GRAPH
    init {
        val workoutId = savedStateHandle.get<Int>("workoutPlanId")
        val workoutPlanNameId = savedStateHandle.get<String>("workoutPlanNameId")

        viewModelScope.launch {
            signUpUseCases.readUserWeightUnitUseCase().collect { weightUnit ->
                _state.update {
                    it.copy(userWeightUnit = weightUnit)
                }
            }
        }

        if (workoutId != null) {
            loadExistingWorkout(workoutId)
        } else if (workoutPlanNameId != null) {
            loadFullPredefinedWorkout(workoutPlanNameId)
        }
    }

    private fun loadExistingWorkout(workoutId: Int) {
        viewModelScope.launch {
            val workoutPlan = workoutPlanUseCases.getWorkoutPlanByIdUseCase(workoutId)

            workoutPlan?.let { workoutPlan ->

                val isWorkoutInDatabase =
                    workoutPlanUseCases.isWorkoutPlanInDatabaseUseCase(workoutId)
                val displayName = getWorkoutPlanDisplayName(
                    context,
                    workoutPlan.workoutPlanNameId,
                    workoutPlan.workoutPlanName
                )
                val displayIconId =
                    workoutPlan.workoutPlanNameId?.let { WorkoutPlanIconMapper.getName(it) }

                _state.update {
                    it.copy(
                        workoutId = workoutId,
                        workoutPlanName = workoutPlan.workoutPlanName,
                        imagePath = workoutPlan.iconUri,
                        workoutPlanNameId = workoutPlan.workoutPlanNameId,
                        workoutPlanIconId = workoutPlan.iconId,
                        displayIconId = displayIconId,
                        displayName = displayName,
                        isWorkoutInDatabase = isWorkoutInDatabase
                    )
                }

                loadWorkoutExercises()

            }
        }
    }

    private fun loadWorkoutExercises() {
        viewModelScope.launch {
            val currentState = _state.value
            currentState.workoutId?.let { id ->
                val workoutExercises =
                    workoutExerciseUseCases.getFullExercisesForWorkoutPlanUseCase(id)
                workoutExercises.collect { workoutExercisesList ->
                    val totalSets = workoutExercisesList.sumOf { it.workoutExercise.sets ?: 0 }
                    val estimatedMinutes = (totalSets * 3.5).roundToInt()

                    _state.update {
                        it.copy(
                            selectedExercises = workoutExercisesList,
                            workoutEstimatedTimeMinutes = estimatedMinutes
                        )
                    }
                }
            }
        }
    }

    private fun loadFullPredefinedWorkout(workoutPlanNameId: String) {
        viewModelScope.launch {
            val workoutExercises =
                PredefinedWorkoutExercises.getExercisesForWorkoutPlan(workoutPlanNameId)
            val displayName =
                getWorkoutPlanDisplayName(context, workoutPlanNameId, _state.value.workoutPlanName)
            val workoutPlanIconId = workoutPlanNameId

            val fullExercises = workoutExercises?.mapNotNull { workoutExercise ->
                val exercise = exerciseUseCases.getExerciseByIdUseCase(workoutExercise.exerciseId)
                exercise?.let { exercise ->
                    WorkoutExerciseWithExerciseInfo(
                        workoutExercise = workoutExercise,
                        exercise = exercise
                    )
                }
            } ?: emptyList()

            val totalSets = fullExercises.sumOf { it.workoutExercise.sets ?: 0 }
            val estimatedMinutes = (totalSets * 3.5f).roundToInt()

            _state.update {
                it.copy(
                    isWorkoutInDatabase = false,
                    workoutPlanNameId = workoutPlanNameId,
                    workoutPlanIconId = workoutPlanIconId,
                    displayName = displayName,
                    displayIconId = workoutPlanIconId.let { nameKey ->
                        WorkoutPlanIconMapper.getName(nameKey)
                    },
                    selectedExercises = fullExercises,
                    workoutEstimatedTimeMinutes = estimatedMinutes
                )
            }
        }
    }

    fun onAction(action: WorkoutPlanDetailAction) {
        when (action) {
            is WorkoutPlanDetailAction.OnDeleteWorkoutClick -> {
                viewModelScope.launch {
                    workoutPlanUseCases.deleteWorkoutPlanByIdUseCase(action.workoutId)
                    _state.update { WorkoutPlanDetailState() }
                }
            }

            is WorkoutPlanDetailAction.OnEditWorkoutClick -> {
                currentWorkoutRepository.setCurrentWorkoutId(action.workoutId)
            }

            is WorkoutPlanDetailAction.OnStartClick.WithWorkoutId -> {

            }

            is WorkoutPlanDetailAction.OnStartClick.WithWorkoutNameId -> {

            }

            is WorkoutPlanDetailAction.DeleteWorkoutExercise -> {
                viewModelScope.launch {
                    workoutExerciseUseCases.deleteWorkoutExerciseByIdUseCase(action.workoutExerciseId)
                }
            }

            is WorkoutPlanDetailAction.OnNavigateBackClick -> Unit

        }
    }
}