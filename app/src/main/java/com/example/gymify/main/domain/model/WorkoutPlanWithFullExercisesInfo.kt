package com.example.gymify.main.domain.model

data class WorkoutPlanWithFullExercisesInfo(
    val workoutPlan: WorkoutPlan,
    val exercises: List<WorkoutExerciseWithExerciseInfo>
)
