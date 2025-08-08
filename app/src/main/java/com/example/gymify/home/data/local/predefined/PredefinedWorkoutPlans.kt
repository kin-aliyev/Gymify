package com.example.gymify.home.data.local.predefined

import com.example.gymify.home.domain.model.WorkoutPlan


object PredefinedWorkoutPlans {

    fun getWorkoutPlan(workoutPlanNameId: String): WorkoutPlan? {
        return predefinedPlans[workoutPlanNameId]
    }

    //TODO add string and drawable res with name workoutplankeys.
    private val predefinedPlans: Map<String, WorkoutPlan> = mapOf(
        // ==========================
        // FULL BODY (1 workout)
        // ==========================
        WorkoutPlanKeys.FULL_BODY_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.FULL_BODY_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.FULL_BODY_FIRST,
        ),

        // ==========================
        // PUSH PUL LEGS (3 workouts)
        // ==========================
        WorkoutPlanKeys.PPL_PUSH_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.PPL_PUSH_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.PPL_PUSH_FIRST
        ),
        WorkoutPlanKeys.PPL_PULL_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.PPL_PULL_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.PPL_PULL_FIRST
        ),
        WorkoutPlanKeys.PPL_LEGS_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.PPL_LEGS_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.PPL_LEGS_FIRST
        ),

        // ==========================
        // Upper-Lower Split (2 workouts)
        // ==========================
        WorkoutPlanKeys.UPPER_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.UPPER_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.UPPER_FIRST
        ),
        WorkoutPlanKeys.LOWER_FIRST to WorkoutPlan(
            id = 0,
            workoutPlanNameId = WorkoutPlanKeys.LOWER_FIRST,
            lastUsedDate = System.currentTimeMillis(),
            iconId = WorkoutPlanKeys.LOWER_FIRST
        ),

    )

// Старый use case
//    private val fullBodyWorkoutPlan = WorkoutPlan(
//        id = 0,
//        workoutPlanNameId = WorkoutPlanKeys.FULL_BODY_FIRST,
//        lastUsedDate = System.currentTimeMillis(),
//        iconId = WorkoutPlanKeys.FULL_BODY_FIRST,
//    )

}

