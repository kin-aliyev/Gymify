package com.example.gymify.main.domain.repository

import com.example.gymify.main.domain.model.WorkoutSession

interface WorkoutSessionRepository {

    suspend fun upsertWorkoutSession(workoutSession: WorkoutSession)

    suspend fun deleteWorkoutSession(sessionId: Int)

    suspend fun getWorkoutSessionById(sessionId: Int): WorkoutSession?

    suspend fun getSessionsByPlanId(planId: Int): List<WorkoutSession>

    suspend fun getSessionsByTimeRange(startTime: Long, endTime: Long): List<WorkoutSession>
}