package com.example.gymify.home.domain.usecases.workout_exercise

import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.home.domain.repository.WorkoutExerciseRepository

class GetWorkoutExercisesByPlanIdUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(planId: Int): List<WorkoutExercise> {
        return repository.getWorkoutExercisesByPlanId(planId)
    }
}