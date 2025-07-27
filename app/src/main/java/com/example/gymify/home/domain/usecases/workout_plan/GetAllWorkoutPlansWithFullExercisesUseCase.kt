package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlanWithFullExercisesInfo
import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow

class GetAllWorkoutPlansWithFullExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    operator fun invoke(): Flow<List<WorkoutPlanWithFullExercisesInfo>> {
        return repository.getAllWorkoutPlansWithFullExercises()
    }
}