package com.example.gymify.main.domain.usecases.workout_exercise

import com.example.gymify.main.domain.repository.WorkoutExerciseRepository

class DeleteWorkoutExerciseByIdUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(workoutExerciseId: Int) {
        repository.deleteWorkoutExerciseById(workoutExerciseId)
    }
}