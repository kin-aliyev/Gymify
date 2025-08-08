package com.example.gymify.home.data.local.predefined

object WorkoutPlanKeys {
    const val FULL_BODY_FIRST = "workout_plan_full_body_01"
    // PPL
    const val PPL_PUSH_FIRST = "workout_plan_ppl_push_01"
    const val PPL_PULL_FIRST = "workout_plan_ppl_pull_01"
    const val PPL_LEGS_FIRST = "workout_plan_ppl_legs_01"

    // Upper Lower
    const val UPPER_FIRST = "workout_plan_upper_01"
    const val LOWER_FIRST = "workout_plan_lower_01"
}

object ProgramCategories {
    val Popular = listOf(
        WorkoutPlanKeys.FULL_BODY_FIRST,
    )

    val PushPullLegs = listOf(
        WorkoutPlanKeys.PPL_PULL_FIRST,
        WorkoutPlanKeys.PPL_PUSH_FIRST,
        WorkoutPlanKeys.PPL_LEGS_FIRST
    )

    val UpperLower = listOf(
        WorkoutPlanKeys.UPPER_FIRST,
        WorkoutPlanKeys.LOWER_FIRST
    )

    val FullBody = listOf(
        WorkoutPlanKeys.FULL_BODY_FIRST,
    )
}