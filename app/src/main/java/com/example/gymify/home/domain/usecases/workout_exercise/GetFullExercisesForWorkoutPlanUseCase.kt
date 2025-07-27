package com.example.gymify.home.domain.usecases.workout_exercise

import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.home.domain.repository.WorkoutExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetFullExercisesForWorkoutPlanUseCase(
    private val repository: WorkoutExerciseRepository
) {
    suspend operator fun invoke(planId: Int): Flow<List<WorkoutExerciseWithExerciseInfo>> {
        return repository.getFullExercisesForWorkoutPlan(planId)
    }
}