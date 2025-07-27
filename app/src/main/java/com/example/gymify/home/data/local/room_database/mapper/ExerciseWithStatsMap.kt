package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.relations.ExerciseWithStats
import com.example.gymify.home.domain.model.ExerciseWithStatsInfo

fun ExerciseWithStats.toDomain(): ExerciseWithStatsInfo {
    return ExerciseWithStatsInfo(
        exercise = this.exercise.toDomain(),
        exerciseStats = this.exerciseStats!!.toDomain()
    )
}