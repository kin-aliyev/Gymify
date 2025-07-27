package com.example.gymify.home.presentation.make_workoutplan_screen

import android.net.Uri

sealed interface MakeWorkoutPlanAction {

    data class OnImageSelected(val uri: Uri) : MakeWorkoutPlanAction

    data class OnWorkoutPlanNameChanged(val planName: String) : MakeWorkoutPlanAction     // Setting the Name for a workout plan
    data class OnSetsChanged(val sets: String) : MakeWorkoutPlanAction     // Setting the number of sets for a specific exercise
    data class OnRepsChanged(val reps: String) : MakeWorkoutPlanAction  // Setting the number of reps for a specific exercise
    data class OnWeightChanged(val weight: String) : MakeWorkoutPlanAction    // Setting the weight for a specific exercise

    data object AddExercise: MakeWorkoutPlanAction
    data object SaveWorkoutPlan : MakeWorkoutPlanAction

    // Actions with added workout exercises
    data class EditExercise(val workoutExerciseId: Int) : MakeWorkoutPlanAction // Редактирование уже добавленного упражнения
    data class DeleteExercise(val workoutExerciseId: Int) : MakeWorkoutPlanAction     // Удаление упражнения из списка выбранных упражнений

    data object CancelEdit : MakeWorkoutPlanAction // Отмена режима редактирования

    object ExitWorkoutPlan : MakeWorkoutPlanAction


    // data class LoadExistingWorkoutPlan(val workoutPlanId: Int) : MakeWorkoutPlanAction // Если изменяем существующий план

}