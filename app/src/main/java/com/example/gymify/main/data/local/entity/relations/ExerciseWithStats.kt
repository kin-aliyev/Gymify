package com.example.gymify.main.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.main.data.local.entity.ExerciseEntity
import com.example.gymify.main.data.local.entity.ExerciseStatsEntity

data class ExerciseWithStats(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "exerciseNameId",
        entityColumn = "exerciseId"
    )
    val exerciseStats: ExerciseStatsEntity?
)
