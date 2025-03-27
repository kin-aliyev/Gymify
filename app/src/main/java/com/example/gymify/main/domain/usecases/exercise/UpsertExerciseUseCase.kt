package com.example.gymify.main.domain.usecases.exercise

import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.repository.ExerciseRepository

class UpsertExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise) {
        repository.upsertExercise(exercise)
    }
}