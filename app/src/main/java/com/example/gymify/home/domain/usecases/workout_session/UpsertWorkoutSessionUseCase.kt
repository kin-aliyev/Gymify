package com.example.gymify.home.domain.usecases.workout_session

import com.example.gymify.home.domain.model.WorkoutSession
import com.example.gymify.home.domain.repository.WorkoutSessionRepository

class UpsertWorkoutSessionUseCase(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(workoutSession: WorkoutSession) {
        repository.upsertWorkoutSession(workoutSession)
    }
}