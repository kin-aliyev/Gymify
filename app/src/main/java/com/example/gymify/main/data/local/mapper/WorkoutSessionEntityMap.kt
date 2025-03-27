package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.WorkoutSessionEntity
import com.example.gymify.main.domain.model.WorkoutSession

fun WorkoutSessionEntity.toDomain(): WorkoutSession {
    return WorkoutSession(
        id = this.id,
        workoutPlanId = this.workoutPlanId,
        timestamp = this.timestamp,
        durationSeconds = this.durationSeconds,
    )
}

fun WorkoutSession.toEntity(): WorkoutSessionEntity {
    return WorkoutSessionEntity(
        id = this.id,
        workoutPlanId = this.workoutPlanId,
        timestamp = this.timestamp,
        durationSeconds = this.durationSeconds
    )
}