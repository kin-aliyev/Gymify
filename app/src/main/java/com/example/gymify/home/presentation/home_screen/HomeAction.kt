package com.example.gymify.home.presentation.home_screen

import com.example.gymify.home.domain.model.WorkoutPlan

sealed interface HomeAction {
    object OnAddNewWorkoutClick : HomeAction

    data class OnWorkoutPlanClick(val workoutPlan: WorkoutPlan) : HomeAction

}