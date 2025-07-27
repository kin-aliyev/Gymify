package com.example.gymify.home.domain.usecases.workout_exercise

import com.example.gymify.home.domain.repository.WorkoutExerciseRepository

class DeleteWorkoutExerciseByIdUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(workoutExerciseId: Int) {
        repository.deleteWorkoutExerciseById(workoutExerciseId)
    }
}