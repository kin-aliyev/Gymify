package com.example.gymify.main.domain.model

data class WorkoutPlanRelationWorkoutExercises (
    val workoutPlan: WorkoutPlan,
    val workoutExercises: List<WorkoutExercise>
)