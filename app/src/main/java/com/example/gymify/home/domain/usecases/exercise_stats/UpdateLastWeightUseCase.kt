package com.example.gymify.home.domain.usecases.exercise_stats

import com.example.gymify.home.domain.repository.ExerciseStatsRepository

class UpdateLastWeightUseCase(
    private val repository: ExerciseStatsRepository
) {
    suspend operator fun invoke(exerciseId: Int, lastWeight: Float, lastWeightReps: Int) {
        repository.updateLastWeight(exerciseId, lastWeight, lastWeightReps)
    }
}