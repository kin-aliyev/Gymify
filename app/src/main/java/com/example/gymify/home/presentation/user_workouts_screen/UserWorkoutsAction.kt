package com.example.gymify.home.presentation.user_workouts_screen

sealed interface UserWorkoutsAction {
    data class OnWorkoutPlanClick(val workoutPlanId: Int) : UserWorkoutsAction
    object OnAddWorkoutClick : UserWorkoutsAction
    object NavigateBack : UserWorkoutsAction
}