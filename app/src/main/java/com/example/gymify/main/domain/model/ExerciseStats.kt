package com.example.gymify.main.domain.model

data class ExerciseStats(
    val id: Int = 0,
    val exerciseId: Int,
    val maxWeight: Float?,
    val maxWeightReps: Int?,
    val lastWeight: Float?,
    val lastWeightReps: Int?
)
