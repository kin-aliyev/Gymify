package com.example.gymify.home.domain.usecases.exercise

import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.repository.ExerciseRepository

class GetExercisesByMuscleGroupUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(muscleGroup: MuscleGroup): List<Exercise> {
        return repository.getExercisesByMuscleGroup(muscleGroup)
    }
}