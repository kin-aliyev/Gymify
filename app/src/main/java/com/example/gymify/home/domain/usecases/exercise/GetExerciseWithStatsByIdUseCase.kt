package com.example.gymify.home.domain.usecases.exercise

import com.example.gymify.home.domain.model.ExerciseWithStatsInfo
import com.example.gymify.home.domain.repository.ExerciseRepository

class GetExerciseWithStatsByIdUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Int): ExerciseWithStatsInfo? {
        return repository.getExerciseWithStatsById(exerciseId)
    }
}