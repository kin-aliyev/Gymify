package com.example.gymify.main.domain.usecases.workout_exercise

import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.main.domain.repository.WorkoutExerciseRepository

class GetWorkoutExerciseWithExerciseUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutExerciseWithExerciseInfo? {
        return repository.getWorkoutExerciseWithExercise(planId)
    }
}