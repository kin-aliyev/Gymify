package com.example.gymify.home.data.repository

import com.example.gymify.home.data.local.room_database.dao.ExerciseStatsDao
import com.example.gymify.home.data.local.room_database.mapper.toDomain
import com.example.gymify.home.data.local.room_database.mapper.toEntity
import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.repository.ExerciseStatsRepository

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