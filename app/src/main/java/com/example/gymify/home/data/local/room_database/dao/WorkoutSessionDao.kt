package com.example.gymify.home.data.local.room_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.gymify.home.data.local.room_database.entity.WorkoutSessionEntity

@Dao
interface WorkoutSessionDao {

    @Upsert
    suspend fun upsertWorkoutSessionEntity(workoutSessionEntity: WorkoutSessionEntity)

    @Query("DELETE FROM workout_sessions WHERE id = :sessionId")
    suspend fun deleteWorkoutSessionById(sessionId: Int)

    @Query("SELECT * FROM workout_sessions WHERE id = :sessionId")
    suspend fun getWorkoutSessionById(sessionId: Int): WorkoutSessionEntity?

    @Query("SELECT * FROM workout_sessions WHERE workoutPlanId = :planId")
    suspend fun getWorkoutSessionsByPlanId(planId: Int): List<WorkoutSessionEntity>

    @Query("SELECT * FROM workout_sessions WHERE timestamp BETWEEN :startTime AND :endTime")
    suspend fun getSessionsByTimeRange(startTime: Long, endTime: Long): List<WorkoutSessionEntity>
}