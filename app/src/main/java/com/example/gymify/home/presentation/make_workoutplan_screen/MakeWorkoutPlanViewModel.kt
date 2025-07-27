package com.example.gymify.home.presentation.make_workoutplan_screen

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.util.ExerciseNameMapper
import com.example.gymify.home.util.MuscleGroupNameMapper
import com.example.gymify.home.data.local.predefined.PredefinedWorkoutExercises
import com.example.gymify.home.util.WorkoutPlanIconMapper
import com.example.gymify.home.util.getWorkoutPlanDisplayName
import com.example.gymify.home.data.repository.util.CurrentWorkoutRepository
import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.usecases.ExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MakeWorkoutPlanViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val currentWorkoutRepository: CurrentWorkoutRepository,
    private val workoutPlanUseCases: WorkoutPlanUseCases,
    private val workoutExerciseUseCases: WorkoutExerciseUseCases,
    private val exerciseUseCases: ExerciseUseCases,
    private val signUpUseCases: SignUpUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(MakeWorkoutPlanState())
    val state = _state.asStateFlow()

    init {
        val workoutPlanNameId = savedStateHandle.get<String>("workoutPlanNameId")
        val exerciseId = savedStateHandle.get<Int>("exerciseId")

        viewModelScope.launch {
            signUpUseCases.readUserWeightUnitUseCase().collect { weightUnit ->
                _state.update { it.copy(userWeightUnit = weightUnit) }
            }
        }

        val workoutId = currentWorkoutRepository.getCurrentWorkoutId()

        if (workoutId != null) {
            loadExistingWorkout(workoutId)
        } else {
            if (workoutPlanNameId != null) {
                createPredefinedWorkoutPlan(workoutPlanNameId)
            } else {
                createNewWorkout()
            }
        }

        if (exerciseId != null) {
            preloadExercise(exerciseId)
        }
    }


    fun createPredefinedWorkoutPlan(workoutPlanNameId: String) {
        viewModelScope.launch {
            try {
                val predefinedPlan = WorkoutPlan(
                    id = 0,
                    workoutPlanNameId = workoutPlanNameId,
                    lastUsedDate = System.currentTimeMillis(),
                    iconId = workoutPlanNameId
                )

                val predefinedPlanId = workoutPlanUseCases.upsertWorkoutPlanUseCase(predefinedPlan)
                currentWorkoutRepository.setCurrentWorkoutId(predefinedPlanId.toInt())

                val displayName = getWorkoutPlanDisplayName(
                    context = context,
                    workoutPlanNameId = predefinedPlan.workoutPlanNameId,
                    workoutPlanName = predefinedPlan.workoutPlanName
                )

                val displayIconId = predefinedPlan.iconId?.let { WorkoutPlanIconMapper.getName(it) }


                _state.update {
                    it.copy(
                        workoutPlanId = predefinedPlanId.toInt(),
                        isPredefinedPlan = true,
                        workoutPlanName = null,
                        workoutPlanIconUri = null,
                        workoutPlanNameId = workoutPlanNameId,
                        displayName = displayName,
                        displayIconId = displayIconId
                    )
                }

                loadPredefinedWorkoutExercises()
            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Error creating predefined workout plan: $e")
            }
        }
    }

    private fun createNewWorkout() {
        viewModelScope.launch {
            val newPlan = WorkoutPlan(
                id = 0,
                workoutPlanName = "",
                lastUsedDate = System.currentTimeMillis()
            )

            val newPlanId = workoutPlanUseCases.upsertWorkoutPlanUseCase(newPlan)

            currentWorkoutRepository.setCurrentWorkoutId(newPlanId.toInt())

            _state.update {
                it.copy(
                    workoutPlanId = newPlanId.toInt(),
                    workoutPlanName = ""
                )
            }
        }
    }

    private fun loadExistingWorkout(workoutId: Int) {
        viewModelScope.launch {
            val workoutPlan = workoutPlanUseCases.getWorkoutPlanByIdUseCase(workoutId)

            workoutPlan?.let { plan ->
                val isPredefined = !plan.workoutPlanNameId.isNullOrEmpty()

                val displayName = getWorkoutPlanDisplayName(
                    context = context,
                    workoutPlanNameId = plan.workoutPlanNameId,
                    workoutPlanName = plan.workoutPlanName
                )

                _state.update {
                    it.copy(
                        workoutPlanId = workoutId,
                        isPredefinedPlan = isPredefined,
                        workoutPlanNameId = plan.workoutPlanNameId,
                        workoutPlanName = plan.workoutPlanName,
                        workoutPlanIconUri = plan.iconUri,
                        displayName = displayName
                    )
                }
            }

            if (_state.value.isPredefinedPlan) {
                loadPredefinedWorkoutExercises()
            } else {
                loadWorkoutExercises()
            }
        }
    }

    fun preloadExercise(exerciseId: Int) {
        viewModelScope.launch {
            try {
                val exercise = exerciseUseCases.getExerciseByIdUseCase(exerciseId)
                exercise?.let {
                    val exerciseName = context.getString(
                        ExerciseNameMapper.getName(it.exerciseNameId)
                    )
                    val muscleGroupName = context.getString(
                        MuscleGroupNameMapper.getName(it.muscleGroup.nameKey)
                    )
                    _state.update { currentState ->
                        currentState.copy(
                            selectedExercise = it,
                            selectedMuscleGroup = it.muscleGroup,
                            selectedExerciseName = exerciseName,
                            selectedMuscleGroupName = muscleGroupName
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Exception: $e")
            }
        }
    }

    private fun startEditingExercise(workoutExerciseId: Int) {
        viewModelScope.launch {
            try {
                val workoutExerciseWithInfo =
                    workoutExerciseUseCases.getWorkoutExerciseWithExerciseUseCase(workoutExerciseId)
                workoutExerciseWithInfo?.let { exerciseInfo ->
                    val exercise = exerciseInfo.exercise
                    val workoutExercise = exerciseInfo.workoutExercise

                    val exerciseName = context.getString(
                        ExerciseNameMapper.getName(exercise.exerciseNameId)
                    )
                    val muscleGroupName = context.getString(
                        MuscleGroupNameMapper.getName(exercise.muscleGroup.nameKey)
                    )

                    _state.update { currentState ->
                        currentState.copy(
                            isEditingMode = true,
                            editingWorkoutExerciseId = workoutExerciseId,
                            selectedExercise = exercise,
                            selectedMuscleGroup = exercise.muscleGroup,
                            selectedExerciseName = exerciseName,
                            selectedMuscleGroupName = muscleGroupName,
                            numberOfSets = workoutExercise.sets?.toString() ?: "",
                            numberOfReps = workoutExercise.reps?.toString() ?: "",
                            weight = workoutExercise.weight?.toString() ?: ""
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Error loading exercise for editing: $e")
            }
        }
    }

    private fun addOrUpdateExercise() {
        val currentState = state.value
        val workoutPlanId = currentState.workoutPlanId
        val selectedExercise = currentState.selectedExercise

        // Проверка обязательных данных
        if (workoutPlanId == 0) {
            Log.e("MakeWorkoutPlanViewModel", "WorkoutPlan ID is invalid")
            return
        }

        if (selectedExercise == null) {
            Log.e("MakeWorkoutPlanViewModel", "No exercise selected")
            return
        }

        // Валидация числовых значений
        val sets = currentState.numberOfSets.toIntOrNull()
        if (sets == null || sets <= 0) {
            Log.e("MakeWorkoutPlanViewModel", "Invalid sets: ${currentState.numberOfSets}")
            return
        }

        val reps = currentState.numberOfReps.toIntOrNull()
        if (reps == null || reps <= 0) {
            Log.e("MakeWorkoutPlanViewModel", "Invalid reps: ${currentState.numberOfReps}")
            return
        }


        val weight: Float? = if (selectedExercise.supportsWeight) {
            val parsedWeight = currentState.weight.toFloatOrNull()
            if (parsedWeight == null || parsedWeight < 0.1f) {
                Log.e("MakeWorkoutPlanViewModel", "Invalid weight: ${currentState.weight}")
                return
            }
            parsedWeight
        } else {
            null // Не требуется вес
        }

        // Всё валидно — создаём объект
        val workoutExercise = WorkoutExercise(
            id = if (currentState.isEditingMode) currentState.editingWorkoutExerciseId ?: 0 else 0,
            workoutPlanId = workoutPlanId,
            exerciseId = selectedExercise.id,
            reps = reps,
            sets = sets,
            weight = weight
        )

        viewModelScope.launch {
            try {
                workoutExerciseUseCases.upsertWorkoutExerciseUseCase(workoutExercise)

                // Сброс состояния после успешного добавления
                _state.update {
                    it.copy(
                        numberOfSets = "",
                        numberOfReps = "",
                        weight = "",
                        selectedExercise = null,
                        selectedMuscleGroup = null,
                        selectedExerciseName = "",
                        selectedMuscleGroupName = "",
                        isEditingMode = false,
                        editingWorkoutExerciseId = null,
                    )
                }
            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Error adding/updating exercise", e)
            }
        }


    }

    private fun cancelEditing() {
        _state.update {
            it.copy(
                numberOfSets = "",
                numberOfReps = "",
                weight = "",
                selectedExercise = null,
                selectedMuscleGroup = null,
                selectedExerciseName = "",
                selectedMuscleGroupName = "",
                isEditingMode = false,
                editingWorkoutExerciseId = null
            )
        }
    }

    private fun handleImageSelection(uri: Uri) {
        viewModelScope.launch {
            try {
                val savedPath = saveImageToInternalStorage(uri)
                _state.update { it.copy(workoutPlanIconUri = savedPath) }

                // Сохраняем в базу данных сразу
                val currentPlan =
                    workoutPlanUseCases.getWorkoutPlanByIdUseCase(state.value.workoutPlanId)
                currentPlan?.let { plan ->
                    workoutPlanUseCases.upsertWorkoutPlanUseCase(
                        plan.copy(iconUri = savedPath)
                    )
                }

            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Exception : $e")
            }
        }
    }

    private suspend fun saveImageToInternalStorage(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val fileName = "workout_plan_${System.currentTimeMillis()}.jpg"
                val file = File(context.filesDir, "workout_images").apply {
                    mkdirs()
                }
                val imageFile = File(file, fileName)

                inputStream?.use { input ->
                    imageFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                imageFile.absolutePath
            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Exception : $e")
                null
            }
        }
    }

    private fun saveWorkoutPlan() {
        viewModelScope.launch {
            val currentState = state.value
            Log.d("saveWorkoutPlan", "Trying to delete plan with id: ${state.value.workoutPlanId}")
            Log.d("saveWorkoutPlan", "isPredefinedPlan: ${currentState.isPredefinedPlan}, selectedExercises: ${state.value.selectedExercises}")
            if (!currentState.isPredefinedPlan && state.value.selectedExercises.isEmpty()) {
                workoutPlanUseCases.deleteWorkoutPlanByIdUseCase(state.value.workoutPlanId)
            } else {
                val updatedPlan = WorkoutPlan(
                    id = currentState.workoutPlanId,
                    workoutPlanName = if (!currentState.isPredefinedPlan) currentState.workoutPlanName else null,
                    lastUsedDate = System.currentTimeMillis(),
                    iconUri = currentState.workoutPlanIconUri,
                )
                workoutPlanUseCases.upsertWorkoutPlanUseCase(updatedPlan)
            }

            currentWorkoutRepository.setCurrentWorkoutId(null)

            _state.update {
                MakeWorkoutPlanState().copy(
                    userWeightUnit = currentState.userWeightUnit
                )
            }
        }
    }

    private fun loadWorkoutExercises() {
        viewModelScope.launch {
            val exercises = workoutExerciseUseCases.getFullExercisesForWorkoutPlanUseCase(
                state.value.workoutPlanId
            )

            exercises.collect { exercisesList ->
                _state.update { it.copy(selectedExercises = exercisesList) }
            }
        }
    }

    private fun loadPredefinedWorkoutExercises() {
        viewModelScope.launch {
            try {
                val currentState = _state.value
                val workoutPlanNameId = currentState.workoutPlanNameId
                val workoutPlanId = currentState.workoutPlanId

                if (workoutPlanNameId == null) {
                    Log.e("MakeWorkoutPlanViewModel", "WorkoutPlanNameId is null")
                    return@launch
                }

                val predefinedExercises =
                    PredefinedWorkoutExercises.getExercisesForWorkoutPlan(workoutPlanNameId)

                predefinedExercises?.let { workoutExercises ->
                    val existingWorkoutExercises =
                        workoutExerciseUseCases.getWorkoutExercisesByPlanIdUseCase(workoutPlanId)

                    if (existingWorkoutExercises.isEmpty()) {
                        workoutExercises.forEach { workoutExercise ->
                            val workoutExercise = workoutExercise.copy(
                                workoutPlanId = workoutPlanId
                            )
                            workoutExerciseUseCases.upsertWorkoutExerciseUseCase(workoutExercise)
                        }
                    }

                    loadWorkoutExercises()
                }
            } catch (e: Exception) {
                Log.e("MakeWorkoutPlanViewModel", "Error loading predefined exercises: $e")
            }
        }
    }

    fun onAction(action: MakeWorkoutPlanAction) {
        when (action) {
            is MakeWorkoutPlanAction.AddExercise -> {
                addOrUpdateExercise()
            }

            is MakeWorkoutPlanAction.DeleteExercise -> {
                viewModelScope.launch {
                    workoutExerciseUseCases.deleteWorkoutExerciseByIdUseCase(action.workoutExerciseId)
                }
            }

            is MakeWorkoutPlanAction.EditExercise -> {
                startEditingExercise(action.workoutExerciseId)
            }

            is MakeWorkoutPlanAction.SaveWorkoutPlan -> {
                saveWorkoutPlan()
            }

            is MakeWorkoutPlanAction.OnImageSelected -> {
                handleImageSelection(action.uri)
            }

            is MakeWorkoutPlanAction.OnRepsChanged -> {
                _state.update { it.copy(numberOfReps = action.reps) }
            }

            is MakeWorkoutPlanAction.OnSetsChanged -> {
                _state.update { it.copy(numberOfSets = action.sets) }
            }

            is MakeWorkoutPlanAction.OnWeightChanged -> {
                _state.update { it.copy(weight = action.weight) }
            }

            is MakeWorkoutPlanAction.OnWorkoutPlanNameChanged -> {
                // Запрещаем изменение имени для встроенных планов
                if (!state.value.isPredefinedPlan) {
                    _state.update { it.copy(workoutPlanName = action.planName) }

                    viewModelScope.launch {
                        val currentPlan =
                            workoutPlanUseCases.getWorkoutPlanByIdUseCase(state.value.workoutPlanId)
                        currentPlan?.let { plan ->
                            workoutPlanUseCases.upsertWorkoutPlanUseCase(
                                plan.copy(workoutPlanName = action.planName)
                            )
                        }
                    }
                }
            }

            is MakeWorkoutPlanAction.CancelEdit -> {
                cancelEditing()
            }

            is MakeWorkoutPlanAction.ExitWorkoutPlan -> {
                viewModelScope.launch {
                    if (!state.value.isPredefinedPlan && state.value.selectedExercises.isEmpty()) {
                        workoutPlanUseCases.deleteWorkoutPlanByIdUseCase(state.value.workoutPlanId)
                    }
                }
                currentWorkoutRepository.setCurrentWorkoutId(null)
            }
        }
    }
}
