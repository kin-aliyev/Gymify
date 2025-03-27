package com.example.gymify.main.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.main.data.local.entity.WorkoutPlanEntity
import com.example.gymify.main.data.local.entity.WorkoutSessionEntity

data class WorkoutPlanWithSessions(
    @Embedded val workoutPlanEntity: WorkoutPlanEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutPlanId"
    )
    val workoutSessionsEntity: List<WorkoutSessionEntity>
)

