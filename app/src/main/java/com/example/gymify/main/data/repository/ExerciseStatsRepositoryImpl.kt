package com.example.gymify.main.data.repository

import com.example.gymify.main.data.local.dao.ExerciseStatsDao
import com.example.gymify.main.data.local.mapper.toDomain
import com.example.gymify.main.data.local.mapper.toEntity
import com.example.gymify.main.domain.model.ExerciseStats
import com.example.gymify.main.domain.repository.ExerciseStatsRepository

class ExerciseStatsRepositoryImpl(
    private val exerciseStatsDao: ExerciseStatsDao
): ExerciseStatsRepository {

    override suspend fun upsertExerciseStats(exerciseStats: ExerciseStats) {
        exerciseStatsDao.upsertExerciseStatsEntity(exerciseStats.toEntity())
    }

    override suspend fun getExerciseStats(exerciseId: Int): ExerciseStats? {
        return exerciseStatsDao.getStatsByExerciseId(exerciseId)?.toDomain()
    }

    override suspend fun updateMaxWeight(exerciseId: Int, maxWeight: Float, maxWeightReps: Int) {
        exerciseStatsDao.updateMaxWeight(exerciseId, maxWeight, maxWeightReps)
    }

    override suspend fun updateLastWeight(exerciseId: Int, lastWeight: Float, lastWeightReps: Int) {
        exerciseStatsDao.updateLastWeight(exerciseId, lastWeight, lastWeightReps)
    }

}