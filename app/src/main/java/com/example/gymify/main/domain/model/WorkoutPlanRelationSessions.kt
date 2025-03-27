package com.example.gymify.main.domain.model

data class WorkoutPlanRelationSessions(
    val workoutPlan: WorkoutPlan,
    val workoutSessions: List<WorkoutSession>
)
