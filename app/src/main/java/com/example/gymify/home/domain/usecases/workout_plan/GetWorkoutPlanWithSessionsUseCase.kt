package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlanRelationSessions
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class GetWorkoutPlanWithSessionsUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutPlanRelationSessions? {
        return repository.getWorkoutPlanWithSessions(planId)
    }
}