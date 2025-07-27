package com.example.gymify.home.presentation.workout_complete_screen

sealed interface WorkoutCompleteAction {
    object OnDoneClick : WorkoutCompleteAction
}