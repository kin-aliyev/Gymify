package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import java.time.Instant

class UpdateLastUsedDateUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(workoutPlanId: Int, timestamp: Long) {
        repository.updateLastUsedDate(workoutPlanId, timestamp)
    }
}