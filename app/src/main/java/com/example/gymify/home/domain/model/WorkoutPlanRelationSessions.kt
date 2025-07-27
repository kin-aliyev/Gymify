package com.example.gymify.home.domain.model

data class WorkoutPlanRelationSessions(
    val workoutPlan: WorkoutPlan,
    val workoutSessions: List<WorkoutSession>
)
