package com.example.gymify.main.domain.usecases.workout_session

import com.example.gymify.main.domain.model.WorkoutSession
import com.example.gymify.main.domain.repository.WorkoutSessionRepository

class GetSessionsByTimeRangeUseCase(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(startTime: Long, endTime: Long): List<WorkoutSession> {
        return repository.getSessionsByTimeRange(startTime, endTime)
    }
}