package com.example.gymify.home.presentation.make_workoutplan_screen

import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo

data class MakeWorkoutPlanState(
    val userWeightUnit: UserWeightUnit = UserWeightUnit.KG, // Получаю через init
    val isPredefinedPlan: Boolean = false,       // Получаю через loadExistingWorkout()
    val workoutPlanId: Int = 0,                  // Устанавливаю через currentWorkoutRepository.

    val workoutPlanName: String? = null,            // User Defined Plan Name
    val workoutPlanIconUri: String? = null,      // User Defined Plan Uri

    val workoutPlanNameId: String? = null,       // Predefined Plan String Res
    val workoutPlanIconId: String? = null,       // Predefined Plan Icon Res

    val displayName: String = "",                // Computed Property for UI (comp. from planName and planNameId)
    val displayIconId: Int? = null,

    // Данные для текущего добавляемого упражнения
    val selectedMuscleGroup: MuscleGroup? = null, // Получаю через preloadExercise()
    val selectedExercise: Exercise? = null,       // Получаю через preloadExercise()
    val selectedMuscleGroupName: String = "",     // Получаю через preloadExercise()
    val selectedExerciseName: String = "",        // Получаю через preloadExercise()

    val numberOfSets: String = "",                // Устанавливается пользователем
    val numberOfReps: String = "",                // Устанавливается пользователем
    val weight: String = "",                      // Устанавливается пользователем

    // Режим редактирования
    val isEditingMode: Boolean = false,           // Флаг - редактируем существующее упражнение или добавляем новое
    val editingWorkoutExerciseId: Int? = null,    // ID редактируемого упражнения (null для нового)

    // List of added exercise
    val selectedExercises: List<WorkoutExerciseWithExerciseInfo> = emptyList(),
)
