package com.example.gymify.home.data.local.predefined

import com.example.gymify.home.domain.model.WorkoutExercise


object PredefinedWorkoutExercises {


    fun getExercisesForWorkoutPlan(workoutPlanNameId: String): List<WorkoutExercise>? {
        return when(workoutPlanNameId) {
            WorkoutPlanKeys.FULL_BODY_FIRST -> fullBodyExercisesFirst
            else -> null
        }
    }

    private val fullBodyExercisesFirst = listOf(
        WorkoutExercise(
            id = 0, // Будет автоматически присвоен при вставке в БД
            workoutPlanId = 0, // Будет обновлен при создании плана
            exerciseId = 1, // ID упражнения "Push-ups"
            reps = 12,
            sets = 3,
            weight = null
        ),
        WorkoutExercise(
            id = 0,
            workoutPlanId = 0,
            exerciseId = 2, // ID упражнения "Squats"
            reps = 15,
            sets = 3,
            weight = null
        ),
        WorkoutExercise(
            id = 0,
            workoutPlanId = 0,
            exerciseId = 3, // ID упражнения "Plank"
            reps = 1,
            sets = 3,
            weight = null // Планка измеряется в секундах, но используем reps
        ),
        WorkoutExercise(
            id = 0,
            workoutPlanId = 0,
            exerciseId = 4, // ID упражнения "Lunges"
            reps = 10,
            sets = 3,
            weight = null
        )
    )
}