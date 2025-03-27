package com.example.gymify.main.data.repository

import com.example.gymify.main.data.local.dao.WorkoutSessionDao
import com.example.gymify.main.data.local.mapper.toDomain
import com.example.gymify.main.data.local.mapper.toEntity
import com.example.gymify.main.domain.model.WorkoutSession
import com.example.gymify.main.domain.repository.WorkoutSessionRepository

class WorkoutSessionRepositoryImpl(
    private val workoutSessionDao: WorkoutSessionDao
): WorkoutSessionRepository {
    override suspend fun upsertWorkoutSession(workoutSession: WorkoutSession) {
        workoutSessionDao.upsertWorkoutSessionEntity(workoutSession.toEntity())
    }

    override suspend fun deleteWorkoutSession(sessionId: Int) {
        workoutSessionDao.deleteWorkoutSessionById(sessionId)
    }

    override suspend fun getWorkoutSessionById(sessionId: Int): WorkoutSession? {
        return workoutSessionDao.getWorkoutSessionById(sessionId)?.toDomain()
    }

    override suspend fun getSessionsByPlanId(planId: Int): List<WorkoutSession> {
        return workoutSessionDao.getWorkoutSessionsByPlanId(planId).map { it.toDomain() }
    }

    override suspend fun getSessionsByTimeRange(
        startTime: Long,
        endTime: Long,
    ): List<WorkoutSession> {
        return workoutSessionDao.getSessionsByTimeRange(startTime, endTime).map { it.toDomain() }
    }

}