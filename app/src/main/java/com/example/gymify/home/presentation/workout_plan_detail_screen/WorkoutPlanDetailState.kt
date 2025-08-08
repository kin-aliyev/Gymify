package com.example.gymify.home.presentation.workout_plan_detail_screen

import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo

data class WorkoutPlanDetailState(
    val isLoading: Boolean = true,
    val isContentReady: Boolean = false,
    val userWeightUnit: UserWeightUnit = UserWeightUnit.KG,

    val workoutId: Int? = null,              // For User Defined plans (передается в Nav Graph)
    val workoutPlanName: String? = null,     // For User Defined plans
    val imagePath: String? = null,           // For User Defined plans

    val workoutPlanNameId: String? = null,   // For Predefined plans (передается в Nav Graph)
    val workoutPlanIconId: String? = null,   // For Predefined plans
    val displayIconId: Int? = null,          // For Predefined plans (используется при отображении фотки)

    val displayName: String = "",

    val isWorkoutInDatabase: Boolean = false,

    val selectedExercises: List<WorkoutExerciseWithExerciseInfo> = emptyList(),

    val workoutEstimatedTimeMinutes: Int = 0,
)