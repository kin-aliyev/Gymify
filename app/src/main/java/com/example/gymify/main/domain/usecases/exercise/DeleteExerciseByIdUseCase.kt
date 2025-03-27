package com.example.gymify.main.domain.usecases.exercise

import com.example.gymify.main.domain.repository.ExerciseRepository

class DeleteExerciseByIdUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Int) {
        repository.deleteExerciseById(exerciseId)
    }
}