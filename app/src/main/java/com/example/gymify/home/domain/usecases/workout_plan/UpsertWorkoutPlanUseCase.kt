package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class UpsertWorkoutPlanUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(workoutPlan: WorkoutPlan): Long {
        return repository.upsertWorkoutPlan(workoutPlan)
    }
}