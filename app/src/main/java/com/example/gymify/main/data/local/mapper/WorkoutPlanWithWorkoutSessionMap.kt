package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.relations.WorkoutPlanWithSessions
import com.example.gymify.main.domain.model.WorkoutPlanRelationSessions

fun WorkoutPlanWithSessions.toDomain(): WorkoutPlanRelationSessions {
    return WorkoutPlanRelationSessions(
        workoutPlan = this.workoutPlanEntity.toDomain(),
        workoutSessions = this.workoutSessionsEntity.map { it.toDomain() }
    )

}