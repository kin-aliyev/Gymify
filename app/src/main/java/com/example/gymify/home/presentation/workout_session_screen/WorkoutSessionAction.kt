package com.example.gymify.home.presentation.workout_session_screen

sealed interface WorkoutSessionAction {
    object StartWorkout : WorkoutSessionAction
    data class FinishWorkout(val seconds: Int) : WorkoutSessionAction

    object MarkMainExerciseCompleted : WorkoutSessionAction
    data class MarkRemainingExerciseCompleted(val exerciseId: Int) : WorkoutSessionAction

    object NavigateBack : WorkoutSessionAction
}