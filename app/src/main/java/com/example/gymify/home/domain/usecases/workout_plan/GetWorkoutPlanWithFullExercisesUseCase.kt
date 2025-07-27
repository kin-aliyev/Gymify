package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlanWithFullExercisesInfo
import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutPlanWithFullExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    operator fun invoke(planId: Int): Flow<WorkoutPlanWithFullExercisesInfo> {
        return repository.getWorkoutPlanWithFullExercises(planId)
    }
}