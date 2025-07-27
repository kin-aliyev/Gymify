package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.home.domain.model.WorkoutPlanRelationWorkoutExercises
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class GetWorkoutPlansWithWorkoutExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(): List<WorkoutPlanRelationWorkoutExercises> {
        return repository.getWorkoutPlansWithWorkoutExercises()
    }
}