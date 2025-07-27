package com.example.gymify.home.domain.usecases.workout_exercise

import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.home.domain.repository.WorkoutExerciseRepository

class GetWorkoutExerciseWithExerciseUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutExerciseWithExerciseInfo? {
        return repository.getWorkoutExerciseWithExercise(planId)
    }
}