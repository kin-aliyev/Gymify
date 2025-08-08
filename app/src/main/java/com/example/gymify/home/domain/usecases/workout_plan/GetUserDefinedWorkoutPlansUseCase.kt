package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow

class GetUserDefinedWorkoutPlansUseCase(
    private val repository: WorkoutPlanRepository
) {
    operator fun invoke(): Flow<List<WorkoutPlan>> {
        return repository.getUserDefinedWorkoutPlans()
    }
}