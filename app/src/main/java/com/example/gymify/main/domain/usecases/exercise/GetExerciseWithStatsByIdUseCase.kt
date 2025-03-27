package com.example.gymify.main.domain.usecases.exercise

import com.example.gymify.main.domain.model.ExerciseWithStatsInfo
import com.example.gymify.main.domain.repository.ExerciseRepository

class GetExerciseWithStatsByIdUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Int): ExerciseWithStatsInfo? {
        return repository.getExerciseWithStatsById(exerciseId)
    }
}