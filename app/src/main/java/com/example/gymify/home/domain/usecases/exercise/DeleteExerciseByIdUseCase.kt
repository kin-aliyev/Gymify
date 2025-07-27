package com.example.gymify.home.domain.usecases.exercise

import com.example.gymify.home.domain.repository.ExerciseRepository

class DeleteExerciseByIdUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Int) {
        repository.deleteExerciseById(exerciseId)
    }
}