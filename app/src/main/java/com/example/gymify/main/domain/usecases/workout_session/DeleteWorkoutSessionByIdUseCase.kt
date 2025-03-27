package com.example.gymify.main.domain.usecases.workout_session

import com.example.gymify.main.domain.repository.WorkoutSessionRepository

class DeleteWorkoutSessionByIdUseCase(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(sessionId: Int) {
        repository.deleteWorkoutSession(sessionId)
    }
}