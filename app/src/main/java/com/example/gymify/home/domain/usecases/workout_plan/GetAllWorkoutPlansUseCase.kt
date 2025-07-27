package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class GetAllWorkoutPlansUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(): List<WorkoutPlan> {
        return repository.getAllWorkoutPlans()
    }
}