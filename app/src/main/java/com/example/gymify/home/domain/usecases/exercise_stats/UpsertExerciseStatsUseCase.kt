package com.example.gymify.home.domain.usecases.exercise_stats

import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.repository.ExerciseStatsRepository

class UpsertExerciseStatsUseCase(
    private val repository: ExerciseStatsRepository
) {
    suspend operator fun invoke(exerciseStats: ExerciseStats) {
        repository.upsertExerciseStats(exerciseStats)
    }
}