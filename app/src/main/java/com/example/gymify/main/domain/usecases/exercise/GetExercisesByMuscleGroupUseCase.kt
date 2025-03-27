package com.example.gymify.main.domain.usecases.exercise

import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.model.MuscleGroup
import com.example.gymify.main.domain.repository.ExerciseRepository

class GetExercisesByMuscleGroupUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(muscleGroup: MuscleGroup): List<Exercise> {
        return repository.getExercisesByMuscleGroup(muscleGroup)
    }
}