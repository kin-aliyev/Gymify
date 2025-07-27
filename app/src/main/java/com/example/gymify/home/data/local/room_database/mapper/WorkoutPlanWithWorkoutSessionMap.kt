package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.relations.WorkoutPlanWithSessions
import com.example.gymify.home.domain.model.WorkoutPlanRelationSessions

fun WorkoutPlanWithSessions.toDomain(): WorkoutPlanRelationSessions {
    return WorkoutPlanRelationSessions(
        workoutPlan = this.workoutPlanEntity.toDomain(),
        workoutSessions = this.workoutSessionsEntity.map { it.toDomain() }
    )

}