package com.example.gymify.home.presentation.home_screen

import com.example.gymify.home.domain.model.WorkoutPlan

data class HomeState(

    val userWorkoutPlans: List<WorkoutPlan> = emptyList(),

)