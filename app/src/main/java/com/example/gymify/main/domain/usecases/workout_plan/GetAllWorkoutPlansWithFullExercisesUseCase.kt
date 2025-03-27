package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.model.WorkoutPlanWithFullExercisesInfo
import com.example.gymify.main.domain.repository.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow

class GetAllWorkoutPlansWithFullExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    operator fun invoke(): Flow<List<WorkoutPlanWithFullExercisesInfo>> {
        return repository.getAllWorkoutPlansWithFullExercises()
    }
}