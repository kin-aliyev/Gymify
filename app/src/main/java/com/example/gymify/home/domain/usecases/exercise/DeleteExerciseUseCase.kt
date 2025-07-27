package com.example.gymify.home.domain.usecases.exercise

import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.repository.ExerciseRepository

class DeleteExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise) {
        repository.deleteExercise(exercise)
    }
}