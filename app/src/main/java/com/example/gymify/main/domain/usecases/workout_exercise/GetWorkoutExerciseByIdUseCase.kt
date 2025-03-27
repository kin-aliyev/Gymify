package com.example.gymify.main.domain.usecases.workout_exercise

import com.example.gymify.main.domain.model.WorkoutExercise
import com.example.gymify.main.domain.repository.WorkoutExerciseRepository

class GetWorkoutExerciseByIdUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(id: Int): WorkoutExercise? {
        return repository.getWorkoutExerciseById(id)
    }
}