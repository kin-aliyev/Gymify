package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.repository.WorkoutPlanRepository
import java.time.Instant

class UpdateLastUsedDateUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(workoutPlanId: Int, timestamp: Instant) {
        repository.updateLastUsedDate(workoutPlanId, timestamp)
    }
}