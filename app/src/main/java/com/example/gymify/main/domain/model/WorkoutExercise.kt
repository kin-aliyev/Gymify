package com.example.gymify.main.domain.model

data class WorkoutExercise(
    val id: Int = 0,
    val workoutPlanId: Int,
    val exerciseId: Int,
    val reps: Int?,
    val sets: Int?,
    val weight: Float?
)
