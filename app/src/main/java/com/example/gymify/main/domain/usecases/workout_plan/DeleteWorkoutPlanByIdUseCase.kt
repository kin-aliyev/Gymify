package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class DeleteWorkoutPlanByIdUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(workoutId: Int) {
        repository.deleteWorkoutPlanById(workoutId)
    }
}