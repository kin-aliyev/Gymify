package com.example.gymify.home.domain.usecases.workout_session

import com.example.gymify.home.domain.model.WorkoutSession
import com.example.gymify.home.domain.repository.WorkoutSessionRepository

class GetSessionsByPlanIdUseCase(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(planId: Int): List<WorkoutSession> {
        return repository.getSessionsByPlanId(planId)
    }
}