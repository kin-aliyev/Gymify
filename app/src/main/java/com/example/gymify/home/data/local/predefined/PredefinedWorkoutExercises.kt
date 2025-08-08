package com.example.gymify.home.data.local.predefined

import com.example.gymify.home.domain.model.WorkoutExercise


object PredefinedWorkoutExercises {

    fun getExercisesForWorkoutPlan(workoutPlanNameId: String): List<WorkoutExercise>? {
        return when(workoutPlanNameId) {
            WorkoutPlanKeys.FULL_BODY_FIRST -> null // TODO add exercises for full body program

            // PPL
            WorkoutPlanKeys.PPL_PUSH_FIRST -> pplPushExercisesFirst
            WorkoutPlanKeys.PPL_PULL_FIRST -> pplPullExercisesFirst
            WorkoutPlanKeys.PPL_LEGS_FIRST -> pplLegsExercisesFirst

            // Upper Lower
            WorkoutPlanKeys.UPPER_FIRST -> upperExercisesFirst
            WorkoutPlanKeys.LOWER_FIRST -> lowerExercisesFirst

            else -> null
        }
    }

}