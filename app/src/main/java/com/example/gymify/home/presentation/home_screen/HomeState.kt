package com.example.gymify.home.presentation.home_screen

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.domain.model.WorkoutPlan

data class HomeState(
    val userExpertiseLevel : ExpertiseLevel = ExpertiseLevel.BEGINNER,
    val userWorkoutPlans: List<WorkoutPlan> = emptyList(),

)