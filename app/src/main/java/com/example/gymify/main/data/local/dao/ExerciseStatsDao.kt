package com.example.gymify.main.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.gymify.main.data.local.entity.ExerciseStatsEntity

@Dao
interface ExerciseStatsDao {

    @Upsert
    suspend fun upsertExerciseStatsEntity(stats: ExerciseStatsEntity)

    @Query("SELECT * FROM exercise_stats WHERE exerciseId = :exerciseId")
    suspend fun getStatsByExerciseId(exerciseId: Int): ExerciseStatsEntity?

    @Query("UPDATE exercise_stats SET maxWeight = :maxWeight, maxWeightReps = :maxWeightReps WHERE exerciseId = :exerciseId AND (maxWeight IS NULL OR maxWeight < :maxWeight OR (maxWeight = :maxWeight AND maxWeightReps < :maxWeightReps))")
    suspend fun updateMaxWeight(exerciseId: Int, maxWeight: Float, maxWeightReps: Int)

    @Query("UPDATE exercise_stats SET lastWeight = :lastWeight, lastWeightReps = :lastWeightReps WHERE exerciseId = :exerciseId")
    suspend fun updateLastWeight(exerciseId: Int, lastWeight: Float, lastWeightReps: Int)

}