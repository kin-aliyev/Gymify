package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class DeleteWorkoutPlanByIdUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(workoutId: Int) {
        repository.deleteWorkoutPlanById(workoutId)
    }
}