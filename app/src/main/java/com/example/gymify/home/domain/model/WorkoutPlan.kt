package com.example.gymify.home.domain.model

import com.example.gymify.core.domain.model.ExpertiseLevel

data class WorkoutPlan(
    val id: Int = 0,
    val workoutPlanNameId: String? = null,
    val workoutPlanName: String? = null,
    val lastUsedDate: Long? = null,
    val iconId: String? = null,
    val iconUri: String? = null,
    val expertiseLevel: ExpertiseLevel? = null
)
