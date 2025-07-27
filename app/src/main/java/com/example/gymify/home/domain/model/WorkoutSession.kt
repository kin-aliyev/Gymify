package com.example.gymify.home.domain.model


data class WorkoutSession(
    val id: Int = 0,
    val workoutPlanId: Int,
    val timestamp: Long,
    val durationSeconds: Long
)
