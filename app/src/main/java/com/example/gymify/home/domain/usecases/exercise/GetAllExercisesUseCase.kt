package com.example.gymify.home.domain.usecases.exercise

import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.repository.ExerciseRepository

class GetAllExercisesUseCase(
    private val repository: ExerciseRepository
) {
    suspend fun invoke(): List<Exercise> {
        return repository.getAllExercises()
    }
}