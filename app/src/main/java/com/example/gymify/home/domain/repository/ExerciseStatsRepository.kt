package com.example.gymify.home.domain.repository

import com.example.gymify.home.domain.model.ExerciseStats

interface ExerciseStatsRepository {

    suspend fun upsertExerciseStats(exerciseStats: ExerciseStats)

    suspend fun getExerciseStats(exerciseId: Int): ExerciseStats?

    suspend fun updateMaxWeight(exerciseId: Int, maxWeight: Float, maxWeightReps: Int)

    suspend fun updateLastWeight(exerciseId: Int, lastWeight: Float, lastWeightReps: Int)
}