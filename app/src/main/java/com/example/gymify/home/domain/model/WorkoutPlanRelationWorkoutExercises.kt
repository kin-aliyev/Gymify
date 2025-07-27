package com.example.gymify.home.domain.model

data class WorkoutPlanRelationWorkoutExercises (
    val workoutPlan: WorkoutPlan,
    val workoutExercises: List<WorkoutExercise>
)