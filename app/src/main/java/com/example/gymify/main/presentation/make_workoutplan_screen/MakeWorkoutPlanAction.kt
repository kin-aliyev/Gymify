package com.example.gymify.main.presentation.make_workoutplan_screen

import com.example.gymify.main.domain.model.MuscleGroup
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo

sealed interface MakeWorkoutPlanAction {

    data class OnExerciseNameKeyChanged(val exerciseNameKey: String) : MakeWorkoutPlanAction


    data object ClickMuscleGroup : MakeWorkoutPlanAction   // Selecting the Muscle Group then Exercise
    data class OnMuscleGroupSelected(val muscleGroup: MuscleGroup) : MakeWorkoutPlanAction
    data class OnExerciseSelected(val stringId: String) : MakeWorkoutPlanAction

    data class OnSetsChanged(val exerciseId: String, val sets: Int?) : MakeWorkoutPlanAction     // Setting the number of sets for a specific exercise
    data class OnRepsChanged(val exerciseId: String, val reps: Int?) : MakeWorkoutPlanAction  // Setting the number of reps for a specific exercise
    data class OnWeightChanged(val exerciseId: String, val weight: Float?) : MakeWorkoutPlanAction    // Setting the weight for a specific exercise

    data class OnWorkoutPlanNameChanged(val workoutId: String, val planName: String) : MakeWorkoutPlanAction     // Setting the Name for a workout plan

    data object AddExercise: MakeWorkoutPlanAction
    data object SaveWorkoutPlan : MakeWorkoutPlanAction

    data class DeleteExercise(val exerciseId: String) : MakeWorkoutPlanAction     // Удаление упражнения из списка выбранных упражнений
    data class EditExercise(val workoutExerciseWithExerciseInfo: WorkoutExerciseWithExerciseInfo) : MakeWorkoutPlanAction // Редактирование уже добавленного упражнения

    data class LoadExistingWorkoutPlan(val workoutPlanId: Int) : MakeWorkoutPlanAction // Если изменяем существующий план


}