package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.WorkoutSessionEntity
import com.example.gymify.home.domain.model.WorkoutSession

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