package com.example.gymify.home.presentation.workout_session_screen

import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo

data class WorkoutSessionState(
    val userWeightUnit: UserWeightUnit = UserWeightUnit.KG,

    val workoutId: Int? = null,              // For User Defined plans (передается в Nav Graph)
    val workoutPlanName: String? = null,     // For User Defined plans
    val workoutPlanNameId: String? = null,   // For Predefined plans (передается в Nav Graph)
    val displayName: String = "",

    val isWorkoutInDatabase: Boolean = false,

    val mainSelectedExercise: WorkoutExerciseWithExerciseInfo? = null,
    val mainSelectedExerciseStats: ExerciseStats? = null,
    val selectedExercises: List<WorkoutExerciseWithExerciseInfo> = emptyList(),

    val isActive: Boolean = false,
    val startTimestamp: Long = 0L,
    val endTimestamp: Long = 0L,
    val timeElapsedSeconds: Int = 0,

    val shouldFinishWorkout: Boolean = false
)