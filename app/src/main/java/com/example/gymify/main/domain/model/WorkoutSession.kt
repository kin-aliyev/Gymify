package com.example.gymify.main.domain.model

import java.time.Instant

data class WorkoutSession(
    val id: Int = 0,
    val workoutPlanId: Int,
    val timestamp: Instant,
    val durationSeconds: Long
)
