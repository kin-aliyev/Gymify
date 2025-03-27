package com.example.gymify.main.domain.model

import com.example.gymify.core.domain.model.ExpertiseLevel
import java.time.Instant

data class WorkoutPlan(
    val id: Int = 0,
    val workoutPlanNameId: String? = null,
    val workoutPlanName: String? = null,
    val lastUsedDate: Instant? = null,
    val iconId: String? = null,
    val iconUri: String? = null,
    val expertiseLevel: ExpertiseLevel? = null
)
