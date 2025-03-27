package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.model.WorkoutPlanRelationSessions
import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class GetWorkoutPlanWithSessionsUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutPlanRelationSessions? {
        return repository.getWorkoutPlanWithSessions(planId)
    }
}