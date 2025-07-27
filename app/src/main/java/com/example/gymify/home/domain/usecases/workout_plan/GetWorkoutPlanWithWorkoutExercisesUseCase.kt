package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlanRelationWorkoutExercises
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class GetWorkoutPlanWithWorkoutExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(planId: Int): WorkoutPlanRelationWorkoutExercises? {
        return repository.getWorkoutPlanWithWorkoutExercises(planId)
    }
}