package com.example.gymify.home.data.local.predefined

import com.example.gymify.home.domain.model.WorkoutPlan


object PredefinedWorkoutPlans {

    fun getWorkoutPlan(workoutPlanNameId: String): WorkoutPlan? {
        return predefinedPlans[workoutPlanNameId]
    }

    private val predefinedPlans: Map<String, WorkoutPlan> = mapOf(
        WorkoutPlanKeys.FULL_BODY_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.FULL_BODY_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.FULL_BODY_FIRST
        )


    )

// Старый use case
//    private val fullBodyWorkoutPlan = WorkoutPlan(
//        id = 0,
//        workoutPlanNameId = WorkoutPlanKeys.FULL_BODY_FIRST,
//        lastUsedDate = System.currentTimeMillis(),
//        iconId = WorkoutPlanKeys.FULL_BODY_FIRST,
//    )

}

