package com.example.gymify.home.data.local.room_database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.home.data.local.room_database.entity.WorkoutPlanEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutSessionEntity

data class WorkoutPlanWithSessions(
    @Embedded val workoutPlanEntity: WorkoutPlanEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutPlanId"
    )
    val workoutSessionsEntity: List<WorkoutSessionEntity>
)

