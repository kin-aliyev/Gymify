package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.model.WorkoutPlanRelationWorkoutExercises
import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class GetWorkoutPlanWithWorkoutExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutPlanRelationWorkoutExercises? {
        return repository.getWorkoutPlanWithWorkoutExercises(planId)
    }
}