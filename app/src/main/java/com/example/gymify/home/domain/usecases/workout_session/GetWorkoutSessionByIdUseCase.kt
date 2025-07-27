package com.example.gymify.home.domain.usecases.workout_session

import com.example.gymify.home.domain.model.WorkoutSession
import com.example.gymify.home.domain.repository.WorkoutSessionRepository

class GetWorkoutSessionByIdUseCase(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(sessionId: Int): WorkoutSession? {
        return repository.getWorkoutSessionById(sessionId)
    }
}