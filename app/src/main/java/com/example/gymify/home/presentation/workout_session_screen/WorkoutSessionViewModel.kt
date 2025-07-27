package com.example.gymify.home.presentation.workout_session_screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.data.local.predefined.PredefinedWorkoutExercises
import com.example.gymify.home.data.local.predefined.PredefinedWorkoutPlans
import com.example.gymify.home.util.getWorkoutPlanDisplayName
import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.model.WorkoutSession
import com.example.gymify.home.domain.usecases.ExerciseStatsUseCases
import com.example.gymify.home.domain.usecases.ExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import com.example.gymify.home.domain.usecases.WorkoutSessionUseCases
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutSessionViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val exerciseStatsUseCases: ExerciseStatsUseCases,
    private val exerciseUseCases: ExerciseUseCases,
    private val workoutExerciseUseCases: WorkoutExerciseUseCases,
    private val workoutPlanUseCases: WorkoutPlanUseCases,
    private val workoutSessionUseCases: WorkoutSessionUseCases,
    private val signUpUseCases: SignUpUseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(WorkoutSessionState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null
    private var workoutStartTimeMillis: Long = savedStateHandle["startTimeMillis"] ?: 0L

    init {
        val workoutPlanId = savedStateHandle.get<Int>("workoutPlanId")
        val workoutPlanNameId = savedStateHandle.get<String>("workoutPlanNameId")

        viewModelScope.launch {
            signUpUseCases.readUserWeightUnitUseCase().collect { weightUnit ->
                _state.update {
                    it.copy(userWeightUnit = weightUnit)
                }
            }
        }

        if (workoutPlanId != null) {
            loadExistingWorkoutPlan(workoutPlanId)
        } else if (workoutPlanNameId != null) {
            loadFullPredefinedWorkout(workoutPlanNameId)
        }

        if (workoutStartTimeMillis != 0L) {
            // Восстанавливаем таймер, если он был активен
            _state.update {
                val elapsed = ((System.currentTimeMillis() - workoutStartTimeMillis) / 1000).toInt()
                it.copy(
                    isActive = true,
                    timeElapsedSeconds = elapsed
                )
            }
            startTimer()
        }
    }

    private fun loadExistingWorkoutPlan(workoutPlanId: Int) {
        viewModelScope.launch {
            val workoutPlan = workoutPlanUseCases.getWorkoutPlanByIdUseCase(workoutPlanId)


            workoutPlan?.let {
                val workoutExercises =
                    workoutExerciseUseCases.getFullExercisesForWorkoutPlanUseCase(workoutPlanId)
                val isInDatabase = workoutPlanUseCases.isWorkoutPlanInDatabaseUseCase(workoutPlanId)
                val displayName = getWorkoutPlanDisplayName(
                    context = context,
                    workoutPlanNameId = workoutPlan.workoutPlanNameId,
                    workoutPlanName = workoutPlan.workoutPlanName
                )

                _state.update {
                    it.copy(
                        workoutId = workoutPlanId,
                        workoutPlanName = workoutPlan.workoutPlanName,
                        workoutPlanNameId = workoutPlan.workoutPlanNameId,
                        isWorkoutInDatabase = isInDatabase,
                        displayName = displayName
                    )
                }

                val completedIds: List<Int> =
                    savedStateHandle["completedExerciseIds"] ?: emptyList()

                workoutExercises.collect { listExercises ->
                    val filteredList = listExercises.filterNot { it.exercise.id in completedIds }

                    val mainExercise = filteredList.firstOrNull()
                    val remainingExercises = filteredList.drop(1)

                    val mainExerciseStats = mainExercise?.let {
                        exerciseStatsUseCases.getExerciseStatsUseCase(it.exercise.id)
                    }

                    _state.update {
                        it.copy(
                            mainSelectedExercise = mainExercise,
                            mainSelectedExerciseStats = mainExerciseStats,
                            selectedExercises = remainingExercises
                        )
                    }
                }
            }
        }
    }

    private fun loadFullPredefinedWorkout(workoutPlanNameId: String) {
        viewModelScope.launch {
            val allPredefinedWorkouts = workoutPlanUseCases.getPredefinedWorkoutPlansUseCase()
            val matchingPlan = allPredefinedWorkouts.find { it.workoutPlanNameId == workoutPlanNameId }

            if (matchingPlan != null) {
                _state.update { it.copy(workoutId = matchingPlan.id) }
            } else {
                val predefinedPlan = PredefinedWorkoutPlans.getWorkoutPlan(workoutPlanNameId)
                predefinedPlan?.let { workoutPlan ->
                    val insertedId = workoutPlanUseCases.upsertWorkoutPlanUseCase(workoutPlan)
                    _state.update { it.copy(workoutId = insertedId.toInt()) }
                }
            }

            val workoutExercises =
                PredefinedWorkoutExercises.getExercisesForWorkoutPlan(workoutPlanNameId)
            val displayName =
                getWorkoutPlanDisplayName(context, workoutPlanNameId, state.value.workoutPlanName)
            val completedIds: List<Int> = savedStateHandle["completedExerciseIds"] ?: emptyList()

            val fullWorkoutExercises = workoutExercises?.mapNotNull { workoutExercise ->
                val exercise = exerciseUseCases.getExerciseByIdUseCase(workoutExercise.exerciseId)
                exercise?.let { exercise ->
                    WorkoutExerciseWithExerciseInfo(
                        exercise = exercise,
                        workoutExercise = workoutExercise
                    )
                }
            } ?: emptyList()

            val filteredFullWorkoutExerciseList =
                fullWorkoutExercises.filterNot { it.exercise.id in completedIds }

            val mainExercise = filteredFullWorkoutExerciseList.firstOrNull()
            val mainExerciseStats = mainExercise?.let {
                exerciseStatsUseCases.getExerciseStatsUseCase(it.exercise.id)
            }
            val remainingExercises = filteredFullWorkoutExerciseList.drop(1)

            _state.update {
                it.copy(
                    workoutPlanNameId = workoutPlanNameId,
                    displayName = displayName,
                    isWorkoutInDatabase = false,
                    mainSelectedExercise = mainExercise,
                    mainSelectedExerciseStats = mainExerciseStats,
                    selectedExercises = remainingExercises

                )
            }
        }

    }


    fun onAction(action: WorkoutSessionAction) {
        when (action) {
            is WorkoutSessionAction.StartWorkout -> {
                startTimer()
            }

            is WorkoutSessionAction.FinishWorkout -> {
                finishWorkout()
            }

            is WorkoutSessionAction.MarkMainExerciseCompleted -> {
                markMainExerciseCompleted()
            }

            is WorkoutSessionAction.MarkRemainingExerciseCompleted -> {
                markRemainingExerciseCompleted(action.exerciseId)
            }

            is WorkoutSessionAction.NavigateBack -> Unit
        }
    }

    private fun startTimer() {
        if (timerJob != null) return

        // Устанавливаем время старта, если его ещё не было
        if (workoutStartTimeMillis == 0L) {
            workoutStartTimeMillis = System.currentTimeMillis()
            savedStateHandle["startTimeMillis"] = workoutStartTimeMillis
        }

        _state.update {
            it.copy(
                isActive = true,
                startTimestamp = workoutStartTimeMillis
            )
        }

        timerJob = viewModelScope.launch {
            while (_state.value.isActive) {
                val elapsed =
                    (((System.currentTimeMillis() - workoutStartTimeMillis) / 1000).toInt())
                _state.update { it.copy(timeElapsedSeconds = elapsed) }
                delay(1000)
            }
        }
    }

    private fun markMainExerciseCompleted() {
        val currentExercise = state.value.mainSelectedExercise ?: return
        val currentId = currentExercise.exercise.id

        val completedIds: List<Int> = savedStateHandle["completedExerciseIds"] ?: emptyList()
        val updatedCompletedList = completedIds + currentId
        savedStateHandle["completedExerciseIds"] = updatedCompletedList

        val selectedExercises = state.value.selectedExercises

        val newMainExercise = selectedExercises.firstOrNull()
        val newRemainingExercises = selectedExercises.drop(1)

        viewModelScope.launch {
            val workoutExercise = currentExercise.workoutExercise
            val currentWeight = workoutExercise.weight
            val currentReps = workoutExercise.reps

            if (currentWeight != null && currentReps != null) {
                val existingStats = exerciseStatsUseCases.getExerciseStatsUseCase(currentId)

                val prevMaxWeight = existingStats?.maxWeight ?: Float.MIN_VALUE
                val prevMaxReps = existingStats?.maxWeightReps ?: 0

                val isNewRecord = currentWeight > prevMaxWeight ||
                        (currentWeight == prevMaxWeight && currentReps > prevMaxReps)

                val newStats = ExerciseStats(
                    id = existingStats?.id ?: 0,
                    exerciseId = currentId,
                    maxWeight = if (isNewRecord) currentWeight else prevMaxWeight,
                    maxWeightReps = if (isNewRecord) currentReps else prevMaxReps,
                    lastWeight = currentWeight,
                    lastWeightReps = currentReps
                )

                exerciseStatsUseCases.upsertExerciseStatsUseCase(newStats)
            }

            val newMainExerciseStats = newMainExercise?.let {
                exerciseStatsUseCases.getExerciseStatsUseCase(it.exercise.id)
            }

            _state.update {
                it.copy(
                    mainSelectedExercise = newMainExercise,
                    mainSelectedExerciseStats = newMainExerciseStats,
                    selectedExercises = newRemainingExercises
                )
            }


            if (newMainExercise == null && newRemainingExercises.isEmpty()) {
                _state.update { it.copy(shouldFinishWorkout = true) }
            }

        }
    }

    private fun markRemainingExerciseCompleted(exerciseId: Int) {
        val currentSelectedList = state.value.selectedExercises.toMutableList()

        val currentSelectedExercise =
            currentSelectedList.firstOrNull { it.exercise.id == exerciseId }

        val updatedList = currentSelectedList.filterNot { it.exercise.id == exerciseId }

        val completedIds: List<Int> = savedStateHandle["completedExerciseIds"] ?: emptyList()
        val updatedCompletedIds = completedIds + exerciseId
        savedStateHandle["completedExerciseIds"] = updatedCompletedIds

        viewModelScope.launch {
            if (currentSelectedExercise != null) {
                val currentWeight = currentSelectedExercise.workoutExercise.weight
                val currentReps = currentSelectedExercise.workoutExercise.reps

                if (currentWeight != null && currentReps != null) {
                    val existingStats = exerciseStatsUseCases.getExerciseStatsUseCase(exerciseId)

                    val prevMaxWeight = existingStats?.maxWeight ?: Float.MIN_VALUE
                    val prevMaxReps = existingStats?.maxWeightReps ?: 0

                    val isNewRecord = currentWeight > prevMaxWeight ||
                            (currentWeight == prevMaxWeight && currentReps > prevMaxReps)

                    val newStats = ExerciseStats(
                        id = existingStats?.id ?: 0,
                        exerciseId = exerciseId,
                        maxWeight = if (isNewRecord) currentWeight else prevMaxWeight,
                        maxWeightReps = if (isNewRecord) currentReps else prevMaxReps,
                        lastWeight = currentWeight,
                        lastWeightReps = currentReps
                    )

                    exerciseStatsUseCases.upsertExerciseStatsUseCase(newStats)
                }
            }
        }

        _state.update { it.copy(selectedExercises = updatedList) }
    }

    private fun finishWorkout() {
        val duration = state.value.timeElapsedSeconds
        timerJob?.cancel()
        timerJob = null

        viewModelScope.launch {
            val workoutSession = state.value.workoutId?.let {
                WorkoutSession(
                    id = 0,
                    workoutPlanId = it,
                    timestamp = savedStateHandle["startTimeMillis"] ?: System.currentTimeMillis(),
                    durationSeconds = duration.toLong()
                )
            }
            workoutSession?.let { workoutSessionUseCases.upsertWorkoutSessionUseCase(it) }
            Log.d("WorkoutSession" ,"Upsert Session $workoutSession")
        }

        _state.update { it.copy(isActive = false, endTimestamp = System.currentTimeMillis()) }

        // Убираем savedStateHandle
        savedStateHandle.remove<Long>("startTimeMillis")
        savedStateHandle.remove<List<Int>>("completedExerciseIds")
        workoutStartTimeMillis = 0L
    }
}