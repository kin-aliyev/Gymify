package com.example.gymify.home.data.local.room_database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.home.data.local.room_database.entity.ExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.ExerciseStatsEntity

data class ExerciseWithStats(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId"
    )
    val exerciseStats: ExerciseStatsEntity?
)
