package com.example.gymify.home.domain.model

data class WorkoutPlanWithFullExercisesInfo(
    val workoutPlan: WorkoutPlan,
    val exercises: List<WorkoutExerciseWithExerciseInfo>
)
