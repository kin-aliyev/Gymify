package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class IsWorkoutPlanInDatabaseUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): Boolean {
        return repository.isWorkoutPlanInDatabase(planId)
    }
}