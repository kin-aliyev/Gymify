package com.example.gymify.main.domain.usecases.exercise

import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.repository.ExerciseRepository

class GetExerciseByIdUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(id: Int): Exercise? {
        return repository.getExerciseById(id)
    }
}