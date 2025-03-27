package com.example.gymify.main.domain.usecases.workout_exercise

import com.example.gymify.main.domain.model.WorkoutExercise
import com.example.gymify.main.domain.repository.WorkoutExerciseRepository

class GetWorkoutExerciseByPlanIdUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(planId: Int): List<WorkoutExercise> {
        return repository.getWorkoutExercisesByPlanId(planId)
    }
}