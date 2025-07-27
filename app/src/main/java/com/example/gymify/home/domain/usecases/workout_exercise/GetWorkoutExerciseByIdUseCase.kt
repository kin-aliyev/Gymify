package com.example.gymify.home.domain.usecases.workout_exercise

import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.home.domain.repository.WorkoutExerciseRepository

class GetWorkoutExerciseByIdUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(id: Int): WorkoutExercise? {
        return repository.getWorkoutExerciseById(id)
    }
}