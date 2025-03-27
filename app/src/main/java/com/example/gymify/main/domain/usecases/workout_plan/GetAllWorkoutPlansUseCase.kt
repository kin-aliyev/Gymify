package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.model.WorkoutPlan
import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class GetAllWorkoutPlansUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(): List<WorkoutPlan> {
        return repository.getAllWorkoutPlans()
    }
}