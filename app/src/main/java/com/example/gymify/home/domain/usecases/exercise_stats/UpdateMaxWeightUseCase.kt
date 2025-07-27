package com.example.gymify.home.domain.usecases.exercise_stats

import com.example.gymify.home.domain.repository.ExerciseStatsRepository

class UpdateMaxWeightUseCase(
    private val repository: ExerciseStatsRepository
) {
    suspend operator fun invoke(exerciseId: Int, maxWeight: Float, maxWeightReps: Int) {
        repository.updateMaxWeight(exerciseId, maxWeight, maxWeightReps)
    }
}