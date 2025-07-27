package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.ExerciseStatsEntity
import com.example.gymify.home.domain.model.ExerciseStats

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