package com.example.gymify.main.domain.usecases.exercise

import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.repository.ExerciseRepository

class GetAllExercisesUseCase(
    private val repository: ExerciseRepository
) {
    suspend fun invoke(): List<Exercise> {
        return repository.getAllExercises()
    }
}