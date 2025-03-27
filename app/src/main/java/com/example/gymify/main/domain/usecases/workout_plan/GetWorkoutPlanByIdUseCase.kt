package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.model.WorkoutPlan
import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class GetWorkoutPlanByIdUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutPlan? {
        return repository.getWorkoutPlanById(planId)
    }
}