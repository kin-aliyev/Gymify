package com.example.gymify.main.domain.usecases.workout_exercise

import com.example.gymify.main.domain.model.WorkoutExercise
import com.example.gymify.main.domain.repository.WorkoutExerciseRepository

class DeleteWorkoutExerciseUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(workoutExercise: WorkoutExercise) {
        repository.deleteWorkoutExercise(workoutExercise)
    }
}