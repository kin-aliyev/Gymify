package com.example.gymify.home.presentation.workout_plan_detail_screen

sealed interface WorkoutPlanDetailAction {
    data class OnDeleteWorkoutClick(val workoutId: Int) : WorkoutPlanDetailAction
    data class OnEditWorkoutClick(val workoutId: Int) : WorkoutPlanDetailAction
    data class OnEditPredefinedWorkoutClick(val workoutPlanNameId: String?) : WorkoutPlanDetailAction

    data class DeleteWorkoutExercise(val workoutExerciseId: Int) : WorkoutPlanDetailAction

    sealed interface OnStartClick : WorkoutPlanDetailAction {
        data class WithWorkoutId(val workoutId: Int) : OnStartClick
        data class WithWorkoutNameId(val workoutNameId: String) : OnStartClick
    }

    object OnNavigateBackClick : WorkoutPlanDetailAction
}