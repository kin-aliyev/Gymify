package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.relations.ExerciseWithStats
import com.example.gymify.main.domain.model.ExerciseWithStatsInfo

fun ExerciseWithStats.toDomain(): ExerciseWithStatsInfo {
    return ExerciseWithStatsInfo(
        exercise = this.exercise.toDomain(),
        exerciseStats = this.exerciseStats!!.toDomain()
    )
}