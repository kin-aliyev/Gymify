package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class GetWorkoutPlanByIdUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutPlan? {
        return repository.getWorkoutPlanById(planId)
    }
}