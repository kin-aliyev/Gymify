package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.ExerciseStatsEntity
import com.example.gymify.main.domain.model.ExerciseStats

fun ExerciseStatsEntity.toDomain(): ExerciseStats {
    return ExerciseStats(
        id = this.id,
        exerciseId = this.exerciseId,
        maxWeight = this.maxWeight,
        maxWeightReps = this.maxWeightReps,
        lastWeight = this.lastWeight,
        lastWeightReps = this.lastWeightReps
    )
}

fun ExerciseStats.toEntity(): ExerciseStatsEntity {
    return ExerciseStatsEntity(
        id = this.id,
        exerciseId = this.exerciseId,
        maxWeight = this.maxWeight,
        maxWeightReps = this.maxWeightReps,
        lastWeight = this.lastWeight,
        lastWeightReps = this.lastWeightReps
    )
}