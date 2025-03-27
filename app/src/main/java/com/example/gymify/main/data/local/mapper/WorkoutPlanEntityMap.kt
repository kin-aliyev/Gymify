package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.WorkoutPlanEntity
import com.example.gymify.main.domain.model.WorkoutPlan

fun WorkoutPlanEntity.toDomain(): WorkoutPlan {
    return WorkoutPlan(
        id = this.id,
        workoutPlanNameId = this.workoutPlanNameId,
        workoutPlanName = this.workoutPlanName,
        lastUsedDate = this.lastUsedDate,
        iconId = this.iconId,
        iconUri = this.iconUri,
        expertiseLevel = this.expertiseLevel
    )
}

fun WorkoutPlan.toEntity(): WorkoutPlanEntity {
    return WorkoutPlanEntity(
        id = this.id ,
        workoutPlanNameId = this.workoutPlanNameId,
        workoutPlanName = this.workoutPlanName,
        lastUsedDate = this.lastUsedDate,
        iconId = this.iconId,
        iconUri = this.iconUri,
        expertiseLevel = this.expertiseLevel
    )
}

