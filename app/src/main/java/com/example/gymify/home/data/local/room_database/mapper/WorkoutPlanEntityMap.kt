package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.WorkoutPlanEntity
import com.example.gymify.home.domain.model.WorkoutPlan

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
        id = this.id,
        workoutPlanNameId = this.workoutPlanNameId,
        workoutPlanName = this.workoutPlanName,
        lastUsedDate = this.lastUsedDate,
        iconId = this.iconId,
        iconUri = this.iconUri,
        expertiseLevel = this.expertiseLevel
    )
}

