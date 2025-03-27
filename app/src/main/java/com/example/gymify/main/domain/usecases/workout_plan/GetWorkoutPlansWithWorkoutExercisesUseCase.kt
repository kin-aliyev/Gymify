package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.main.domain.model.WorkoutPlanRelationWorkoutExercises
import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class GetWorkoutPlansWithWorkoutExercisesUseCase(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(): List<WorkoutPlanRelationWorkoutExercises> {
        return repository.getWorkoutPlansWithWorkoutExercises()
    }
}