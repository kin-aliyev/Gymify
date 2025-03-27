package com.example.gymify.main.domain.usecases.exercise_stats

import com.example.gymify.main.domain.repository.ExerciseStatsRepository

class UpdateMaxWeightUseCase(
    private val repository: ExerciseStatsRepository
) {
    suspend operator fun invoke(exerciseId: Int, maxWeight: Float, maxWeightReps: Int) {
        repository.updateMaxWeight(exerciseId, maxWeight, maxWeightReps)
    }
}