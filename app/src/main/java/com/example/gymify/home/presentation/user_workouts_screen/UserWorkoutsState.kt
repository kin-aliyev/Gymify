package com.example.gymify.home.presentation.user_workouts_screen

import com.example.gymify.home.domain.model.WorkoutPlan

data class UserWorkoutsState(
    val userWorkoutPlans: List<WorkoutPlan> = emptyList()
)