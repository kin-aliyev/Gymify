package com.example.gymify.home.domain.usecases.exercise_stats

import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.repository.ExerciseStatsRepository

class GetExerciseStatsUseCase(
    private val repository: ExerciseStatsRepository
) {
    suspend operator fun invoke(exerciseId: Int): ExerciseStats? {
        return repository.getExerciseStats(exerciseId)
    }
}