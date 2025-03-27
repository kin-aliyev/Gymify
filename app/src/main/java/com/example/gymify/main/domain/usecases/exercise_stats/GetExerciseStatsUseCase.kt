package com.example.gymify.main.domain.usecases.exercise_stats

import com.example.gymify.main.domain.model.ExerciseStats
import com.example.gymify.main.domain.repository.ExerciseStatsRepository

class GetExerciseStatsUseCase(
    private val repository: ExerciseStatsRepository
) {
    suspend operator fun invoke(exerciseId: Int): ExerciseStats? {
        return repository.getExerciseStats(exerciseId)
    }
}